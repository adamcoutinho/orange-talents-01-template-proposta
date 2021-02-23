package br.com.zup.propostas.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;



@Entity
@Table(name = "solicitante")
public class Solicitante implements Serializable {
    
    @Deprecated
	public Solicitante() {
		
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_solicitante")
    @SequenceGenerator(name = "sequence_solicitante", sequenceName = "sq_solicitante", allocationSize = 1)
    private Long id;
    
    @NotBlank(message = "informe o documento.")  
    private String documento;
    
    @NotBlank(message = "informe o email.")
    @Email(message = "informe um email valido.")
    private String email;
    
    @NotBlank(message = "informe o nome.")    
    private String nome;
         
    @Positive
    @NotNull(message = "salário não pode ser nulo.")
    private BigDecimal salario;

    @Embedded
    private Endereco endereco; 

    @OneToOne(mappedBy = "solicitante")
	@JsonIgnore
    private Proposta proposta;
    
    

	public Solicitante(@NotBlank(message = "informe o documento.") String documento,
			@NotBlank(message = "informe o email.") @Email(message = "informe um email valido.") String email,
			@NotBlank(message = "informe o nome.") String nome,
			@Positive @NotNull(message = "salário não pode ser nulo.") BigDecimal salario,@NotNull(message = "id endereço não pode ser nulo.") Endereco endereco
			) {
		
		this.documento = documento;
		this.email = email;
		this.nome = nome;		
		this.salario = salario;
		this.salario=salario;
		this.endereco =endereco;
	}



//	public Long getId() {
//		return id;
//	}



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



	public Endereco getEndereco() {
		return endereco;
	}



	public Proposta getProposta() {
		return proposta;
	}



	

	
    
    

}
