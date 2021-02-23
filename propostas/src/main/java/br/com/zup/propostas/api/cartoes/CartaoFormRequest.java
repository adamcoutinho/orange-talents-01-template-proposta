package br.com.zup.propostas.api.cartoes;

import br.com.zup.propostas.core.models.Proposta;

public class CartaoFormRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public CartaoFormRequest(Proposta proposta) {
        this.documento = proposta.getSolicitante().getDocumento();
        this.nome = proposta.getSolicitante().getNome();
        this.idProposta = proposta.getUuid();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
