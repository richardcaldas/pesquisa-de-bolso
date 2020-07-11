package com.pesquisadebolso.utils;

import com.pesquisadebolso.configuration.AppConfig;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public final class EncryptorUtils {

    public static String encrypt(String text) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(AppConfig.ENCRYPTION_KEY);
        encryptor.setAlgorithm(AppConfig.ALGORITHM);

        return encryptor.encrypt(text);
        
    }
    
}