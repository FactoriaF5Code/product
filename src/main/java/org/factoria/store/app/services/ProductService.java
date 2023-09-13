package org.factoria.store.app.services;

import org.factoria.store.app.repositories.Product;
import org.factoria.store.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(@Autowired ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product saveProduct(Product product) {
        return repository.save(product);
    }
}
