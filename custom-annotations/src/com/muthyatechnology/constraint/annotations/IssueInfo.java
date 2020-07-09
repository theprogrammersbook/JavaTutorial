package com.muthyatechnology.constraint.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface IssueInfo {
	 public enum Type {
	        BUG, IMPROVEMENT, FEATURE
	    }

	    Type type() default Type.BUG;

	    String reporter() default "Vimesh";

	    String created() default "10/01/2015";
}
