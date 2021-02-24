package br.com.zup.propostas.api.cartoes;

public class SolicitacaoAvisoViagemFormRequest {

    private String destino;

    private String validoAte;

    public SolicitacaoAvisoViagemFormRequest(String destino, String validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public String getValidoAte() {
        return validoAte;
    }
}
