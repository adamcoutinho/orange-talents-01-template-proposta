package br.com.zup.propostas.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NaoExisteNumeroCartaoValidator implements ConstraintValidator<NaoExisteNumeroCartao,String> {
    private final Logger logger = LoggerFactory.getLogger(NaoExisteNumeroCartaoValidator.class);
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(NaoExisteNumeroCartao constraintAnnotation) {

    }

    @Override
    public boolean isValid(String numeroCartao, ConstraintValidatorContext context) {

        Query query  =this.manager.createQuery("SELECT 1 FROM Proposta p WHERE p.idCartao = :identificadorCartao")
                .setParameter("identificadorCartao",numeroCartao);
        if(query.getResultList().size()==0){
            logger.error("erro: número de cartão não existe.");
            return false;
        }

        return true;
    }
}
