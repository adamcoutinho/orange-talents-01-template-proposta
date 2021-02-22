package br.com.zup.propostas.core.biometria;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Base64;

@Component
public class VerificaBiometriaValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return BiometriaFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        BiometriaFormRequest request = (BiometriaFormRequest) target;

        if (!org.apache.commons.codec.binary.Base64.isBase64(Base64.getEncoder().encode(request.getBiometria().getBytes()))) {
            errors.rejectValue("biometria", null, "biometria não está no formato valido.");
        }
    }
}
