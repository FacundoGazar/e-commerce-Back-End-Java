package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> listProducts(String name, Double price);

    Product editProduct(Integer id, Product dataProduct);

    Product deleteProduct(Integer id);

    Product viewProductDetail(Integer id);
}
