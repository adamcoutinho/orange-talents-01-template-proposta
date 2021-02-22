package br.com.zup.propostas.models;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Table(name = "endereco")
@Embeddable
public class Endereco implements Serializable {
		
	
	@Deprecated
	public Endereco() {
	}



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

	public Endereco(
			@NotBlank(message = "informe um cep.") String cep,
			@NotBlank(message = "informe um logradouro.") String logradouro,
			@NotNull(message = "informe um número.") Long numero,
			@NotBlank(message = "informe um bairro.") String bairro,
			@NotBlank(message = "informe uma cidade") String cidade,
			@NotBlank(message = "informe um estado.") String estado

			) {
		
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.bairro=bairro;
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
