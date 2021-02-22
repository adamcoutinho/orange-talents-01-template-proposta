package br.com.zup.propostas.shared;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistId, Long> {

    @PersistenceContext
    private EntityManager manager;
    private Class<?> kclass;
    private String fieldName;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        this.kclass = constraintAnnotation.domainClass();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Long idObject, ConstraintValidatorContext constraintValidatorContext) {
        Query query = this.manager.createQuery("SELECT 1 FROM " + kclass.getName() + " WHERE " + fieldName + " = :id");
        query.setParameter("id", idObject);
        if(query.getResultList().size()==0){
            return false;
        }
        return true;
    }
}
