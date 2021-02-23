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
import java.util.UUID;
@Entity
@Table(name = "carteira_sansung")
public class CarteiraSansung {

    @Deprecated
    public CarteiraSansung() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_carteira_sansung")
    @SequenceGenerator(name = "sequence_carteira_sansung", sequenceName = "sq_carteira_sansung", allocationSize = 1)
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    @Email
    @NotBlank
    private String email;

    @OneToOne
    private Cartao cartao;

    public CarteiraSansung(@Email @NotBlank String email, Cartao cartao) {
        this.email = email;
        this.cartao = cartao;
    }

    public String getUuid() {
        return uuid;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
