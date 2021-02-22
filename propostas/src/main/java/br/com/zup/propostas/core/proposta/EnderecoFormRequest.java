package br.com.zup.propostas.core.proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoFormRequest {
	
	@NotBlank(message = "informe um cep.")
	private String cep;
	
	@NotBlank(message = "informe um logradouro.")
	private String logradouro;
	
	@NotNull(message = "informe um número.")
	private Long numero;
	
	@NotBlank(message = "informe um bairro.")
	private String bairro;
	
	@NotBlank(message = "informe uma cidade")
	private String cidade;
	
	@NotBlank(message = "informe um estado.")
	private String estado;

	public Endereco toModel() {
		return new Endereco(this.cep,this.logradouro,this.numero,this.bairro,this.cidade,this.estado);
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public Long getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	
	
	
}
