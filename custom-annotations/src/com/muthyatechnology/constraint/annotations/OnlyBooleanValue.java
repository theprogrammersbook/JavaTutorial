package com.muthyatechnology.constraint.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { OnlyBooleanValueValidator.class })
public @interface OnlyBooleanValue {
    String message() default "Only Boolean values are accepted";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}