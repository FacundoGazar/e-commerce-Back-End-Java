package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProducts();

    void deleteProduct(Product product);

    Product saveProduct(Product product);

    Optional<Product> getProductById(Integer id);

    List<Product> findByNameContaining(String name);

    List<Product> findByPriceLessThanEqual(Double price);

    List<Product> findByNameContainingAndPriceLessThanEqual(String name, Double price);
}
