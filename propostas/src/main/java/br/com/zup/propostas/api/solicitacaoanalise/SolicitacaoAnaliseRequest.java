package br.com.zup.propostas.api.solicitacaoanalise;

import br.com.zup.propostas.models.Proposta;

public class SolicitacaoAnaliseRequest {

	private String documento;

	private String nome;

	private String idProposta;

	private StatusSolicitacao resultadoSolicitacao;

	public SolicitacaoAnaliseRequest() {

	}

	public SolicitacaoAnaliseRequest(Proposta proposta) {
		this.documento = proposta.getSolicitante().getDocumento();
		this.nome = proposta.getSolicitante().getNome();
		this.idProposta = proposta.getUuidProposta();
	}

	public String getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(String idProposta) {
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public void setResultadoSolicitacao(StatusSolicitacao resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "SolicitacaoAnalise [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}





}
