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
        return client.sql("INSERT INTO payments (user_id, product_name) VALUES (:userId, :productName)")
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
