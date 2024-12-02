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