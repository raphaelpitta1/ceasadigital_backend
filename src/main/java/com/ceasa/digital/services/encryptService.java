package com.ceasa.digital.services;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class encryptService {


    private String textopuro;
    static String IV = "AAAAAAAAAAAAAAAA";
                       
    static String chaveencriptacao="0123456789abcdef";

    public encryptService() {
        
    }

    public String getTextopuro() {
        return textopuro;
    }


    public static String encrypt(String textopuro) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        byte[] senhaEncripta = encripta.doFinal(textopuro.getBytes("UTF-8"));
        return new String(senhaEncripta).toUpperCase();
       
      }
     
      public static String decrypt(byte[] textoencriptado) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
       
        return new String(decripta.doFinal(textoencriptado),"UTF-8");
      }



}
