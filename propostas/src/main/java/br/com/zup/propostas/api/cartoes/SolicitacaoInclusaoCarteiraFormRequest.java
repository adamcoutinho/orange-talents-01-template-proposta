package br.com.zup.propostas.api.cartoes;

public class SolicitacaoInclusaoCarteiraFormRequest {

    private String email;

    private String carteira;

    public SolicitacaoInclusaoCarteiraFormRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
