package br.edu.ifpb.sicAgro.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.edu.ifpb.sicAgro.validation.UniqueCPFProdutor;

public class ValidadUniqueCPFFuncionario implements ConstraintValidator<UniqueCPFProdutor, String>{
	
	@Override
	public void initialize(UniqueCPFProdutor constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//Produtor produtor = funcionarioService.findByCPF(value);
		
		return false;
	}

}
