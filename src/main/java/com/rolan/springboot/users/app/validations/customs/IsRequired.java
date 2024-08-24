package com.rolan.springboot.users.app.validations.customs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy=IsRequiredValidation.class)
public @interface IsRequired {

    String message() default "es obligatorio, anotación perzonalizada";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

}
