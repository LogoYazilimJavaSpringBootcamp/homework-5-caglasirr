package com.example.movieapp.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Encryptor {

    public String encryptGivenPassword (String email, String password) {

        int hash_password = email.hashCode() * password.hashCode();

        return String.valueOf(hash_password);
//        MessageDigest md = MessageDigest.getInstance("MD5");
//
//        byte[] messageDiggest = md.digest(password.getBytes());
//
//        BigInteger bigInteger = new BigInteger(1, messageDiggest);
//
//        return bigInteger.toString(16);
    }
}
