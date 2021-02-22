package br.com.zup.propostas.core.proposta;

import br.com.zup.propostas.models.Proposta;

import javax.persistence.EntityManager;
import javax.validation.Valid;

public class PropostaFormRequest {

	@Valid
	private SolicitanteFormRequest solicitanteFormRequest ;

	@Valid
	public  EnderecoFormRequest enderecoFormRequest;

	public Proposta toModel() {
		return new Proposta(this.solicitanteFormRequest.toModel( this.enderecoFormRequest.toModel()));
	}

	public Boolean existeDocumento(EntityManager manager) {
		 if(!manager.createQuery("SELECT p  FROM Proposta p WHERE p.solicitante.documento like :documento")
		 .setParameter("documento",this.solicitanteFormRequest.getDocumento())
		 .getResultList()
		 .isEmpty()) return true; 
		 
		 return false;
				
	}

	public SolicitanteFormRequest getSolicitanteFormRequest() {
		return solicitanteFormRequest;
	}


	public EnderecoFormRequest getEnderecoFormRequest() {
		return enderecoFormRequest;
	}




}
