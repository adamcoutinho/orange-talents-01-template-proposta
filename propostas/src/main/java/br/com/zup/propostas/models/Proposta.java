package br.com.zup.propostas.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.zup.propostas.api.cartoes.CartaoDetailResponse;
import br.com.zup.propostas.api.solicitacaoanalise.SolicitacaoAnaliseResponse;
import br.com.zup.propostas.api.solicitacaoanalise.StatusSolicitacao;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Deprecated
    public Proposta() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_proposta")
    @SequenceGenerator(name = "sequence_proposta", sequenceName = "sq_proposta", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String uuidProposta = UUID.randomUUID().toString();

    @OneToOne(cascade = CascadeType.PERSIST)
    private Solicitante solicitante;

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

//    @PrimaryKeyJoinColumn
    private String idCartao ;

//    @OneToOne(mappedBy = "proposta")
//    private Cartao cartao;

    public Proposta(Solicitante solicitante) {

        this.solicitante = solicitante;
    }

    public void atualizaStatusProposta(SolicitacaoAnaliseResponse solicitacaoAnaliseResponse) {

        if (solicitacaoAnaliseResponse.getResultadoSolicitacao().equals(StatusSolicitacao.COM_RESTRICAO)) {
            this.status = StatusProposta.NAO_ELEGIVEL;
        }
        if (solicitacaoAnaliseResponse.getResultadoSolicitacao().equals(StatusSolicitacao.SEM_RESTRICAO)) {
            this.status = StatusProposta.ELEGIVEL;
        }

    }

    public boolean elegivel() {
        return this.status.equals(StatusProposta.ELEGIVEL);
    }

    public boolean naoElegivel() {
        return this.status.equals(StatusProposta.NAO_ELEGIVEL);
    }

    public String getUuidProposta() {
        return uuidProposta;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }


    public StatusProposta getStatus() {
        return status;
    }


    public void adicionarIdCartao(CartaoDetailResponse response) {
        this.idCartao = response.getId();
    }

    public String getIdCartao() {
        return idCartao;
    }


    public boolean naoPossuiNumeroCartao() {
        return this.idCartao == null;
    }
}
