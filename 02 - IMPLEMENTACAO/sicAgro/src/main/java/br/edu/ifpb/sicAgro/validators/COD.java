package br.edu.ifpb.sicAgro.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * Anotação para validação do código do produto, usando bean validation
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "([a-zA-Z]{2}\\d{4,18})?")
public @interface COD {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "{br.edu.ifpb.sicAgro.constraints.COD.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
