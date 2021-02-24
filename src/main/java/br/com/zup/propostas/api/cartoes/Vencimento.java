package br.com.zup.propostas.api.cartoes;

import java.time.LocalDate;

public class Vencimento {

    private String id;

    private Long dia;

    private LocalDate dataCriacao;


    public String getId() {
        return id;
    }

    public Long getDia() {
        return dia;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
