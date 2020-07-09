package com.muthyatechnology.constraint.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyIntegerValueValidator implements ConstraintValidator<OnlyIntegerValue, Integer> {
   	@Override
	public void initialize(OnlyIntegerValue arg0) {
		
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		 if(value!=null){
			 return value instanceof Integer; 
		 }else{
			return true; 
		 }
		
	}
}