package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.Product;
import com.ecommerce.e_commerce.repositories.ProductRepository;
import com.ecommerce.e_commerce.repositories.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public Product createProduct(Product product) {
        return this.repository.saveProduct(product);
    }

    public List<Product> listProducts(String name, Double price) {
        if (name != null && (price != null && price > 0)) {
            return this.repository.findByNameContainingAndPriceLessThanEqual(name, price);
        } else if (name != null) {
            return this.repository.findByNameContaining(name);
        } else if (price != null && price > 0) {
            return this.repository.findByPriceLessThanEqual(price);
        }

        return this.repository.getProducts();
    }

    public Product editProduct(Integer id, Product dataProduct) {
        Product product = this.repository.getProductById(id).
                orElseThrow(() -> new RuntimeException("No encontramos el producto"));

        if (dataProduct.getName() != null && !(dataProduct.getName().isBlank())) {
            product.setName(dataProduct.getName());
        } else {
            System.out.println("Nombre invalido");
        }

        if (dataProduct.getPrice() != null && dataProduct.getPrice() >= 0) {
            product.setPrice(dataProduct.getPrice());
        } else {
            System.out.println("Precio invalido");
        }

        if (dataProduct.getDesc() != null && !(dataProduct.getDesc().isBlank())) {
            product.setDesc(dataProduct.getDesc());
        } else {
            System.out.println("Descripcion invalida");
        }

        this.repository.saveProduct(product);

        return product;
    }

    public Product deleteProduct(Integer id) {
        Optional<Product> productOptional = this.repository.getProductById(id);
        if (productOptional.isEmpty()) {
            return null;
        }

        Product product = productOptional.get();

        this.repository.deleteProduct(product);
        return product;
    }

    public Product viewProductDetail(Integer id){
        return this.repository.getProductById(id).
                orElseThrow(() -> new RuntimeException("No se encontró el producto ingresado"));
    }
}
