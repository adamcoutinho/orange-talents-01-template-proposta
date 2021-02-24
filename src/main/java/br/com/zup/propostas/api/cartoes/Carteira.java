package br.com.zup.propostas.api.cartoes;

import java.time.LocalDate;

public class Carteira {

    private String id;

    private String email;

    private LocalDate associadaEm;

    private String emissor;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getAssociadaEm() {
        return associadaEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
