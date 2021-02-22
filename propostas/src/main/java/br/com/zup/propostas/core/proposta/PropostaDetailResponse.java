package br.com.zup.propostas.core.proposta;

import br.com.zup.propostas.models.Proposta;

public class PropostaDetailResponse {

	private String documento;
	
	private String nome;
	
	private String uuidProposta;

	public PropostaDetailResponse(Proposta proposta) {
		this.documento = proposta.getSolicitante().getDocumento();
		this.nome = proposta.getSolicitante().getNome();
		this.uuidProposta = proposta.getUuidProposta();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getUuidProposta() {
		return uuidProposta;
	}
}
