package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryJPA extends JpaRepository<Product, Integer> {

    List<Product> findByNameContaining(String name);

    List<Product> findByPriceLessThanEqual(Double price);

    List<Product> findByNameContainingAndPriceLessThanEqual(String name, Double price);
}
