package com.rolan.springboot.users.app.services;

import java.util.List;
import java.util.Optional;

import com.rolan.springboot.users.app.entities.Product;

public interface ProductService {

    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    Optional<Product> delete(Long id);
    Optional<Product> update(Long id, Product product);

}
