package br.com.zup.propostas.core.biometria;

import br.com.zup.propostas.core.proposta.Cartao;
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

    public Biometria toModel(EntityManager manager, String identificadorCartao) {
        Cartao cartao  = (Cartao) manager.createQuery("SELECT c FROM Cartao c WHERE c.identificadorCartao like :numero_cartao")
                .setParameter("numero_cartao",identificadorCartao)
                .getSingleResult();

        return new Biometria(cartao,Base64.getEncoder().encodeToString(biometria.getBytes()));
    }
}
