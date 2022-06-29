package com.teraenergy.bisolution;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JasyptTest {
    public static void main(String[] args) {

        String password = "tera";

        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword(password);
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encryptedText = jasypt.encrypt(password);
        String decryptedText = jasypt.decrypt(encryptedText);

        System.out.println("encryptedText = " + encryptedText);
        System.out.println("decryptedText = " + decryptedText);
    }
}
