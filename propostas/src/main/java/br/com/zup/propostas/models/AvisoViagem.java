package br.com.zup.propostas.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

public class AvisoViagem {

    @Deprecated
    public AvisoViagem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_avisoviagem")
    @SequenceGenerator(name = "sequence_avisoviagem", sequenceName = "sq_avisoviagem", allocationSize = 1)
    private Long id;

    private Instant instant = Instant.now();

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

    @ManyToOne
    private Cartao cartao;


    public AvisoViagem(@NotBlank String identificadorCartao, @NotBlank String destinoViagem, String ipAdress, String userAgent, @Future LocalDate dateTerminoViagem) {
        this.identificadorCartao = identificadorCartao;
        this.destinoViagem = destinoViagem;
        this.ipAdress = ipAdress;
        this.userAgent = userAgent;
        this.dateTerminoViagem = dateTerminoViagem;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDate getDateTerminoViagem() {
        return dateTerminoViagem;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
