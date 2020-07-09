package com.muthyatechnology.constraint.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyStringValueValidator implements ConstraintValidator<OnlyStringValue, String> {

	@Override
	public void initialize(OnlyStringValue arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value!=null){
			 return value instanceof String; 
		 }else{
			return true; 
		 }
	}

}
