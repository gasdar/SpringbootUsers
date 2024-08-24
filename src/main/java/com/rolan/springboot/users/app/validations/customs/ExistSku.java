package com.rolan.springboot.users.app.validations.customs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
@Constraint(validatedBy=ExistSkuValidation.class)
public @interface ExistSku {

    String message() default "ya existe en la base de datos";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };

}
