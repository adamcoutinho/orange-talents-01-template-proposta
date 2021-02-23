package br.com.zup.propostas.core.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "carteira_paypal")
public class CarteiraPaypal {

    @Deprecated
    public CarteiraPaypal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_carteira_paypal")
    @SequenceGenerator(name = "sequence_carteira_paypal", sequenceName = "sq_carteira_paypal", allocationSize = 1)
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    @NotBlank
    @Email
    private String email;

    @OneToOne
    @NotNull
    private Cartao cartao;

    public CarteiraPaypal(@NotBlank @Email String email, @NotNull Cartao cartao) {
        this.email = email;
        this.cartao = cartao;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Long getId() {
        return id;
    }
}
