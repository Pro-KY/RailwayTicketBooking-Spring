package com.proky.booking.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {
    public String encrypt(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
