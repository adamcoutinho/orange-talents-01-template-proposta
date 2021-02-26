package br.com.zup.propostas.config.conf;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptConfig {


    @Value("${jasypt}")
    private String JASYPT_PASSWORD="asdaszxczx";

    @Bean
    public AES256TextEncryptor get(){
        AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
        System.out.println(this.JASYPT_PASSWORD);
        aes256TextEncryptor.setPassword(this.JASYPT_PASSWORD);
        return aes256TextEncryptor;
    }

}
