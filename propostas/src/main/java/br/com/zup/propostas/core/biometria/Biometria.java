package br.com.zup.propostas.core.biometria;

import br.com.zup.propostas.core.proposta.Cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biometria")
public class Biometria {

    @Deprecated
    public Biometria() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_biometria")
    @SequenceGenerator(name = "sequence_biometria", sequenceName = "sq_biometria", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String uuidBiometria = UUID.randomUUID().toString();

    private LocalDate dataRegistro = LocalDate.now();

    @NotEmpty(message = "identificador do cartão vazio.")
    private String identificadorCartao;

    @NotEmpty(message = "finger print biometria vazia.")
    private String fingerPrint;

    @ManyToOne
    private Cartao cartao;

    public Biometria(Cartao cartao, @NotEmpty(message = "finger print biometria vazia.") String fingerPrint) {
        this.cartao=cartao;
        this.identificadorCartao = cartao.getIdentificadorCartao();
        this.fingerPrint = fingerPrint;
    }

//    getters


    public String getUuidBiometria() {
        return uuidBiometria;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
