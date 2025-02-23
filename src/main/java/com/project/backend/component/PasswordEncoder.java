package com.project.backend.component;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class PasswordEncoder {

    // 비밀번호 해싱
    public String[] encrypt(String password) {
        try {
            byte[] salt = generateSalt();
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            String hashedPassword = Base64.getEncoder().encodeToString(hash);
            String saltString = Base64.getEncoder().encodeToString(salt);
            return new String[] { hashedPassword, saltString };  // 해시와 솔트를 배열로 반환
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    // 랜덤 솔트 생성
    private byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    // 입력된 비밀번호와 저장된 솔트를 사용하여 해싱하는 메서드
    public String encryptWithSalt(String password, String saltString) {
        try {
            byte[] salt = Base64.getDecoder().decode(saltString);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 100000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash); // 해시값 반환
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
