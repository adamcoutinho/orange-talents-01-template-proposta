package br.com.zup.propostas.api.solicitacaoanalise;

public class SolicitacaoAnaliseResponse {
	private String documento;

	private String nome;

	private String idProposta;

	private StatusSolicitacao resultadoSolicitacao;

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public StatusSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "SolicitacaoAnaliseResponse [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}

	
}
