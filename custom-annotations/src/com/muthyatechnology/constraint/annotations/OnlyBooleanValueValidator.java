package com.muthyatechnology.constraint.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OnlyBooleanValueValidator implements ConstraintValidator<OnlyBooleanValue, Boolean> {

	@Override
	public void initialize(OnlyBooleanValue arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Boolean value, ConstraintValidatorContext context) {
		if(value!=null){
			 return value instanceof Boolean; 
		 }else{
			return true; 
		 }
	}

}
