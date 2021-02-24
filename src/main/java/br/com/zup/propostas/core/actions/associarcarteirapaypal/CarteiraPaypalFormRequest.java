package br.com.zup.propostas.core.actions.associarcarteirapaypal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraPaypalFormRequest {

    @NotBlank(message = "informe um email.")
    @Email(message = "informe um email valido.")
    private String email;

    public String getEmail() {
        return email;
    }
}
