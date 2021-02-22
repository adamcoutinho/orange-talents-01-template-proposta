package br.com.zup.propostas.shared;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@CPF(message = "informe um cpf valido.")
@CNPJ(message = "informe um cnpj valido.")
@ConstraintComposition(CompositionType.OR)
public @interface DocumentoIdenfitificacao {
	
	String message() default "informe um CPF ou CNPJ válido.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
