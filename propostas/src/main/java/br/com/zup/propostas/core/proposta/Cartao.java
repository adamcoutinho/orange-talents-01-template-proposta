package br.com.zup.propostas.core.proposta;

import br.com.zup.propostas.core.biometria.Biometria;
import br.com.zup.propostas.core.bloqueiocartao.BloqueioCartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cartao")
public class Cartao {


    @Deprecated
    public Cartao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_cartao")
    @SequenceGenerator(name = "sequence_cartao", sequenceName = "sq_cartao", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String uuidCartao = UUID.randomUUID().toString();

    @Column(unique = true)
    private String identificadorCartao;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;


    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias;

    @OneToMany(mappedBy = "cartao")
    private List<BloqueioCartao> bloqueios;


//    @OneToOne
//    @JoinColumn(name = "idcartao")
//    private Proposta proposta;

    public Cartao(String identificadorCartao) {
        this.identificadorCartao = identificadorCartao;
        this.statusCartao = StatusCartao.DESBLOQUEADO;
    }

    public String getUuidCartao() {
        return uuidCartao;
    }

    public String getIdentificadorCartao() {
        return identificadorCartao;
    }

    public StatusCartao getStatusCartao() {
        return statusCartao;
    }

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public boolean bloqueado(){
        return this.statusCartao.equals(StatusCartao.BLOQUEADO);
    }
    public boolean desbloqueado(){
        return this.statusCartao.equals(StatusCartao.DESBLOQUEADO);
    }

    public List<BloqueioCartao> getBloqueios() {
        return bloqueios;
    }

    public Cartao bloquear() {
        this.statusCartao = StatusCartao.BLOQUEADO;
        return this;
    }


}
