package br.edu.ifpb.sicAgro.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.validation.UniqueCPFProdutor;

public class ValidadUniqueCPFProdutor implements ConstraintValidator<UniqueCPFProdutor, String>{
	
	@Inject
	private ProdutorService produtorService;

	@Override
	public void initialize(UniqueCPFProdutor constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return !produtorService.isCPFExists(value);
	}

}
