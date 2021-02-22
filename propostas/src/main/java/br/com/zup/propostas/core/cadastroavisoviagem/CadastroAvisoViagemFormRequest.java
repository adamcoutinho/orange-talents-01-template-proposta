package br.com.zup.propostas.core.cadastroavisoviagem;

import br.com.zup.propostas.models.AvisoViagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CadastroAvisoViagemFormRequest {

    @NotBlank
    private String identificadorCartao;

    @NotBlank
    private String destinoViagem;

    @NotBlank
    private String ipAdress;

    @NotBlank
    private String userAgent;

    @Future
    private LocalDate dateTerminoViagem;

    public AvisoViagem toModel(HttpServletRequest httpServletRequest) {
        return new AvisoViagem();

    }
}
