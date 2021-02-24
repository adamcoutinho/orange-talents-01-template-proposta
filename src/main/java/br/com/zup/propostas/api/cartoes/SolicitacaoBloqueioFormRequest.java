package br.com.zup.propostas.api.cartoes;

public class SolicitacaoBloqueioFormRequest {

    private String sistemaResponsavel;

    public SolicitacaoBloqueioFormRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
