package br.com.zup.propostas.core.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.propostas.models.Endereco;
import br.com.zup.propostas.models.Solicitante;
import br.com.zup.propostas.shared.DocumentoIdenfitificacao;

public class SolicitanteFormRequest {
	
	@DocumentoIdenfitificacao
	@NotBlank(message = "informe o documento.")
	private String documento;
	
	@Email(message = "informe um email valido.")
	@NotBlank(message = "informe o email.")	
	private String email;
	
	@NotBlank(message = "informe um nome.")
	private  String nome;
	
	@Positive
	@NotNull(message = "salário não pode ser nulo.")
	private BigDecimal salario;
	
	public Solicitante toModel(Endereco endereco) {
		return new Solicitante(this.documento,this.email,this.nome,this.salario,endereco);
	}
	
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

}