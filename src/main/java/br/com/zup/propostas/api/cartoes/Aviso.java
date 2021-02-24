package br.com.zup.propostas.api.cartoes;

import java.time.LocalDate;

public class Aviso {

    private LocalDate validoAte;

    private String destino;

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
