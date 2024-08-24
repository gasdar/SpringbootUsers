package com.rolan.springboot.users.app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rolan.springboot.users.app.entities.Product;

@Component
public class ProductValidation implements Validator {

    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "message.custom");
        
        if(product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", "message.custom");
        }

        if(product.getPrice() == null) {
            errors.rejectValue("price", "message.custom");
        } else if(product.getPrice() < 500) {
            errors.rejectValue("price", "message.validate.price");
        }
    }

}
