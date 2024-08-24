package com.rolan.springboot.users.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.rolan.springboot.users.app.entities.Product;
import com.rolan.springboot.users.app.helpers.ResquestHelper;
import com.rolan.springboot.users.app.services.ProductService;
// import com.rolan.springboot.users.app.validations.ProductValidation;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // @Autowired
    // private ProductValidation validation;
    
    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> list(@PathVariable Long id) {
        Optional<Product> optional = service.findById(id);
        if(optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(optional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult bindingResult) {
        // validation.validate(product, bindingResult);
        if(bindingResult.hasErrors()) {
            return ResquestHelper.getErrorsFromBody(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult, @PathVariable Long id) {
        // validation.validate(product, bindingResult);
        if(bindingResult.hasErrors()) {
            return ResquestHelper.getErrorsFromBody(bindingResult);
        }
        Optional<Product> optionalProd = service.update(id, product);
        if(optionalProd.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProd.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> optional = service.delete(id);
        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(optional.get());
    }

}
