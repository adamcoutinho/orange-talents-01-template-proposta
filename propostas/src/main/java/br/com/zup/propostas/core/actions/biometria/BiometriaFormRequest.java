package br.com.zup.propostas.core.actions.biometria;

import br.com.zup.propostas.core.models.Cartao;
import br.com.zup.propostas.core.models.Biometria;
import br.com.zup.propostas.shared.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import java.util.Base64;

public class BiometriaFormRequest {

    @NotEmpty
    @UniqueValue(domainClass = Biometria.class , fieldName = "fingerPrint")
    private String biometria;

    public String getBiometria() {
        return biometria;
    }

    public Biometria toModel(Cartao cartao) {

        return new Biometria(cartao,Base64.getEncoder().encodeToString(biometria.getBytes()));
    }
}
