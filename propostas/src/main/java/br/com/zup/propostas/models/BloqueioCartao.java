package br.com.zup.propostas.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "bloqueio_cartao")
public class BloqueioCartao {

    @Deprecated
    public BloqueioCartao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_biometria")
    @SequenceGenerator(name = "sequence_biometria", sequenceName = "sq_biometria", allocationSize = 1)
    private Long id;

    @NotBlank
    private String uuidBloqueioCartao = UUID.randomUUID().toString();

    private Instant instant = Instant.now();
    @NotBlank
    private String identificadorCartao;
    @NotBlank
    private String ipAddress;
    @NotBlank
    private String userAgent;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    public BloqueioCartao(@NotBlank String identificadorCartao, @NotBlank String ipAddress, @NotBlank String userAgent, @NotNull Cartao cartao) {
        this.identificadorCartao = identificadorCartao;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public String getUuidBloqueioCartao() {
        return uuidBloqueioCartao;
    }

    public Instant getInstant() {
        return instant;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
