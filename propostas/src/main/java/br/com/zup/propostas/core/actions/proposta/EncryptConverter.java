package br.com.zup.propostas.core.actions.proposta;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class EncryptConverter implements AttributeConverter<String,String> {

    @Autowired
    private AES256TextEncryptor aes256TextEncryptor;
    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return this.aes256TextEncryptor.encrypt(attribute);
    }


}
