package com.rolan.springboot.users.app.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rolan.springboot.users.app.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    boolean existsBySku(String sku);
    
}
