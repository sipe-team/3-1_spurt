package com.order.perf.common.aes256;

import java.security.GeneralSecurityException;

public interface EncryptionService {

    String encrypt(String plainText) throws GeneralSecurityException;

    String decrypt(String plainText) throws GeneralSecurityException;

}
