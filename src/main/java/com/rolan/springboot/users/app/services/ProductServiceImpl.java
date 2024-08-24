package com.rolan.springboot.users.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rolan.springboot.users.app.entities.Product;
import com.rolan.springboot.users.app.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    /*
    NOTAS:
    1. La inyección de depencia nos instancia los objetos que son componentes, para
    utilizar su instancia, y no realizar por nuestra cuenta la creación del
    construtor vacío instanciando los objetos necesarios.
    2. Las clases servicios reflejan la lógica de negocio donde podemos consultar
    distintos objetos repositorios para obtener información de la base de datos y
    de esta manera realizar distintos procedimientos dentro de la transacción de
    un método.

    */
    @Autowired
    private ProductRepository repository;

    // MÉTODOS TRANSACCIONALES IMPORTANTE QUE SEAN DE SPRINGBOOT
    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Transactional(readOnly=true)
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent()) {
            Product productDB = optionalProduct.get();
            productDB.setName(product.getName());
            productDB.setPrice(product.getPrice());
            productDB.setDescription(product.getDescription());
            productDB.setSku(product.getSku());
            return Optional.of(repository.save(productDB));
        }
        return optionalProduct;
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = repository.findById(id);
        productOptional.ifPresent(productDb -> {
            repository.delete(productDb);
        });
        return productOptional;
    }

    @Transactional(readOnly=true)
    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }

}
