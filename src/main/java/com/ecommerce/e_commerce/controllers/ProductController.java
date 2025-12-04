package com.ecommerce.e_commerce.controllers;

import com.ecommerce.e_commerce.models.entities.Product;
import com.ecommerce.e_commerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return this.service.createProduct(product);
    }

    @GetMapping
    public List<Product> listProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price) {
        return this.service.listProducts(name, price);
    }

    @GetMapping("/{id}")
    public Product viewProductDetail(@PathVariable Integer id) {
        return this.service.viewProductDetail(id);
    }

    @PutMapping("/{id}")
    public Product editProduct(@PathVariable Integer id, @RequestBody Product product) {
        return this.service.editProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Integer id) {
        return this.service.deleteProduct(id);
    }
}
