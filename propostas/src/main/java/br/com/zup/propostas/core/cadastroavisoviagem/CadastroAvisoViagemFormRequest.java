package br.com.zup.propostas.core.cadastroavisoviagem;

import br.com.zup.propostas.models.AvisoViagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CadastroAvisoViagemFormRequest {



    @Future
    private LocalDate dateTerminoViagem;

    @NotBlank
    private String destinoViagem;

    public LocalDate getDateTerminoViagem() {
        return dateTerminoViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }
}
