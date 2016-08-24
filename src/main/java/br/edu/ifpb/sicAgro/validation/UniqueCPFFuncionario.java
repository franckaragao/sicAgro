package br.edu.ifpb.sicAgro.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.edu.ifpb.sicAgro.validation.impl.ValidadUniqueCPFProdutor;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidadUniqueCPFProdutor.class)	
public @interface UniqueCPFFuncionario {
	
	String message() default "Já existe um Funcionário cadastrado com este CPF";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
