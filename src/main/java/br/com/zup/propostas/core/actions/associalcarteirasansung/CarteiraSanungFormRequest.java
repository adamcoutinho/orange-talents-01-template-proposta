package br.com.zup.propostas.core.actions.associalcarteirasansung;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraSanungFormRequest {


    private String identificadorCartao;

    @NotBlank(message = "informe um email.")
    @Email(message = "informe um email valido.")
    private String email;


    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getEmail() {
        return email;
    }
}
