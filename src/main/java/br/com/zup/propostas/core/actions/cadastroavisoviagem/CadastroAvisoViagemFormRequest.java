package br.com.zup.propostas.core.actions.cadastroavisoviagem;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CadastroAvisoViagemFormRequest {

    @Future(message = "informe uma data posterior a atual.")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate dataTerminoViagem;

    @NotBlank(message = "informe o destino.")
    private String destinoViagem;

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }
}
