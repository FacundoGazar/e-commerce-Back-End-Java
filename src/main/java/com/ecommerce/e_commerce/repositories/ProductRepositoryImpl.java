package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductRepositoryJPA productRepository;

    public ProductRepositoryImpl(ProductRepositoryJPA productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> findByNameContaining(String name) {
        return this.productRepository.findByNameContaining(name);
    }

    public List<Product> findByPriceLessThanEqual(Double price) {
        return this.productRepository.findByPriceLessThanEqual(price);
    }

    public List<Product> findByNameContainingAndPriceLessThanEqual(String name, Double price) {
        return this.productRepository.findByNameContainingAndPriceLessThanEqual(name, price);
    }

    public void deleteProduct(Product product) {
        this.productRepository.delete(product);
    }

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Optional<Product> getProductById(Integer id) {
        return this.productRepository.findById(id);
    }
}
