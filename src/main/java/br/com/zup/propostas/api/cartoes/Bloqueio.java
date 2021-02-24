package br.com.zup.propostas.api.cartoes;

import java.time.LocalDate;

public class Bloqueio {

    private String id;

    private LocalDate bloqueadoEm;

    private String sistemaResponsavel;

    private Boolean ativo;

    public String getId() {
        return id;
    }

    public LocalDate getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
