
# 레디스를 사용해서 샤드 분산하여 조회 성능 증가시키기
> 스퍼트 5주차

---

## 1. 개요

- **샤드 관리**: 데이터베이스 샤드 간의 데이터 분산을 관리합니다.
- **Redis 통합**: 샤드 분산률 정보를 Redis에 동적으로 저장 및 관리합니다.
- **분산률 기반 샤드 선택**: 데이터가 균형 있게 저장되도록 가장 적게 사용된 샤드를 선택합니다.

---

## 2. Redis 구조

### 2.1 샤드 분산률

Redis는 샤드 분산률 정보를 `Hash` 구조로 저장합니다.

- **키**: `shard:distribution`
- **값**: 샤드 ID와 해당 샤드의 분산률을 매핑.

예시:
```json
{
  "0": 10,
  "1": 15,
  "2": 5
}
```

---

## 3. 구현 세부사항

### 3.1 RedisShardRoutingService

Redis에 저장된 샤드 분산률 데이터를 관리합니다.

```java
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisShardRoutingService {

    private static final String SHARD_DISTRIBUTION_KEY = "shard:distribution";
    private final StringRedisTemplate redisTemplate;

    public RedisShardRoutingService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Map<Integer, Integer> getShardDistribution() {
        Map<Object, Object> rawDistribution = redisTemplate.opsForHash().entries(SHARD_DISTRIBUTION_KEY);
        return rawDistribution.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.getKey().toString()),
                        entry -> Integer.parseInt(entry.getValue().toString())
                ));
    }

    public int getOptimalShardId() {
        Map<Integer, Integer> distribution = getShardDistribution();
        return distribution.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("No shard distribution available"))
                .getKey();
    }

    public void incrementShardDistribution(int shardId) {
        redisTemplate.opsForHash().increment(SHARD_DISTRIBUTION_KEY, String.valueOf(shardId), 1);
    }

    public void decrementShardDistribution(int shardId) {
        redisTemplate.opsForHash().increment(SHARD_DISTRIBUTION_KEY, String.valueOf(shardId), -1);
    }
}
```
- 레디스에 샤드 분산 정보를 저장하여 분산율에 따라 적절하게 샤딩 분산을 합니다.

---

### 3.2 ShardRoutingService

샤드 클라이언트를 분산률 데이터에 따라 선택합니다.

```java
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class ShardRoutingService {

    private final RedisShardRoutingService redisShardRoutingService;
    private final Map<Integer, DatabaseClient> shardClients;

    public ShardRoutingService(RedisShardRoutingService redisShardRoutingService, Map<Integer, DatabaseClient> shardClients) {
        this.redisShardRoutingService = redisShardRoutingService;
        this.shardClients = shardClients;
    }

    public int getShardIdBasedOnDistribution() {
        return redisShardRoutingService.getOptimalShardId();
    }

    public DatabaseClient resolveShardBasedOnDistribution() {
        int shardId = getShardIdBasedOnDistribution();
        return shardClients.get(shardId);
    }

    public DatabaseClient getClientForShard(int shardId) {
        if (!shardClients.containsKey(shardId)) {
            throw new IllegalArgumentException("Invalid shard ID: " + shardId);
        }
        return shardClients.get(shardId);
    }
}
```

---

### 3.3 OrderService

샤드 관리 기능을 사용하여 주문 데이터를 저장하고 검색합니다.

```java
package com.order.perf.application;

import com.order.perf.common.aes256.EncryptionService;
import com.order.perf.common.shard.ShardRoutingService;
import com.order.perf.domain.Payment;
import com.order.perf.domain.repository.PaymentRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.security.GeneralSecurityException;
import java.util.UUID;

@Transactional(readOnly = true)
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ShardRoutingService shardRoutingService;
    private final EncryptionService encryptionService;


    public PaymentService(PaymentRepository paymentRepository, ShardRoutingService shardRoutingService,
                          EncryptionService encryptionService) {
        this.paymentRepository = paymentRepository;
        this.shardRoutingService = shardRoutingService;
        this.encryptionService = encryptionService;
    }

    public Mono<Void> savePayment(Payment payment) {
        // 1. 샤드 클라이언트 결정
        DatabaseClient client = shardRoutingService.resolveShard();
        payment.declareEncryptedShardRoutingId(shardRoutingService.getShardIdForClient(client) + ":" + generateShortUuid());

        // 2. 데이터 저장
        return client.sql("INSERT INTO payments (user_id, ...) VALUES (:userId, ...)")
                .bind("id", payment.getId())
                .then()
                .doOnSuccess(unused -> {
                    // 3. 샤드 분산률 업데이트
                    int shardId = shardRoutingService.getShardIdForClient(client);
                    shardRoutingService.incrementShardDistribution(shardId);
                });
    }

    public Mono<Payment> getPaymentById(String encryptedId) throws GeneralSecurityException {
        // 1. ID 복호화
        String decryptedId = encryptionService.decrypt(encryptedId);

        // 2. 복호화된 ID에서 샤드 키와 실제 ID 추출
        String[] parts = decryptedId.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid ID format");
        }

        String shardKey = parts[0]; // 샤드 키

        // 3. 샤드 클라이언트 결정
        int shardId = Integer.parseInt(shardKey);
        DatabaseClient client = shardRoutingService.getClientForShard(shardId);

        // 4. 데이터 조회
        return client.sql("SELECT * FROM payments WHERE id = :id")
                .bind("id", encryptedId)
                .map((row, metadata) -> {
                    Payment payment = new Payment();
                    // ...
                    return payment;
                })
                .one();
    }

    private String generateShortUuid() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

}
```
- `getPaymentById`를 통해 주문 내역, 지불 내역, 배달 내역 등 저장 이후 값이 변경될 가능성이 적은 데이터를 빠르게 조회할 수 있다.

## 4. 동적 IV 적용하여 암호화 랜덤성 보장
``` java
package com.order.perf.common.aes256;

import com.order.perf.constant.Properties;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.SecureRandom;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Resource
    private final Properties.Aes256Configs aes256;
    private final Key keySpec;

    // AES 블록 크기
    private static final int IV_SIZE = 16;

    public EncryptionServiceImpl(Properties.Aes256Configs aes256) {
        this.aes256 = aes256;
        this.keySpec = new SecretKeySpec(this.aes256.getKEY().getBytes(StandardCharsets.UTF_8), "AES");
    }

    @Override
    public String encrypt(String plainText) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 랜덤 IV 생성
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 암호화
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // IV와 암호문 결합
        byte[] ivAndCipher = new byte[IV_SIZE + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndCipher, 0, IV_SIZE);
        System.arraycopy(encryptedBytes, 0, ivAndCipher, IV_SIZE, encryptedBytes.length);

        // Base64로 인코딩하여 반환
        ByteBuf encoded = Base64.encode(Unpooled.wrappedBuffer(ivAndCipher));
        return encoded.toString(StandardCharsets.UTF_8);
    }

    @Override
    public String decrypt(String cipherText) throws GeneralSecurityException {
        // Base64 디코딩
        ByteBuf decoded = Base64.decode(Unpooled.wrappedBuffer(cipherText.getBytes(StandardCharsets.UTF_8)));
        byte[] ivAndCipher = new byte[decoded.readableBytes()];
        decoded.readBytes(ivAndCipher);

        // IV와 암호문 분리
        byte[] iv = new byte[IV_SIZE];
        byte[] encryptedBytes = new byte[ivAndCipher.length - IV_SIZE];
        System.arraycopy(ivAndCipher, 0, iv, 0, IV_SIZE);
        System.arraycopy(ivAndCipher, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        // 복호화
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
        byte[] plainBytes = cipher.doFinal(encryptedBytes);

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}
```
- IV를 랜덤으로 부여함으로 매번 다른 암호화 값을 노출하여 보안성을 높였습니다.

---

## 5. 요약

- **균형 잡힌 샤드 선택**: Redis에 저장된 분산률 데이터를 활용하여 데이터를 균형 있게 저장합니다.
- **확장성**: 새로운 샤드 추가와 분산 로직 변경이 간단히 가능합니다.
- **보안성**: 암호화된 ID를 지원하여 데이터의 보안을 강화합니다.