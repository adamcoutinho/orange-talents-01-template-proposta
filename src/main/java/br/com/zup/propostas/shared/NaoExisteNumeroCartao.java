package br.com.zup.propostas.shared;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {NaoExisteNumeroCartaoValidator.class})
@Target({ FIELD,METHOD,PARAMETER})
@Retention(RUNTIME)
public @interface NaoExisteNumeroCartao {
    String message() default "número de cartão inexistente.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
