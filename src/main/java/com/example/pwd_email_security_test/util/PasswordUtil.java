package com.example.pwd_email_security_test.util;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    public static String generateTempPassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getEncoder().withoutPadding().encodeToString(bytes).substring(0, length);
    }
}
