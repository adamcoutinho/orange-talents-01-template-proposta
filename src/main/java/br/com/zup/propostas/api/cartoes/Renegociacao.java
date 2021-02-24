package br.com.zup.propostas.api.cartoes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Renegociacao {
    private String id;

    private Long quantidade;

    private BigDecimal valor;

    private LocalDate dataCriacao;

    public String getId() {
        return id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
