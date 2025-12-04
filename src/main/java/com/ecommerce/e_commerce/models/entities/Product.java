package com.ecommerce.e_commerce.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String desc;
    private Double price;
    private Integer stock;
    private String category;
    private String image;

    public Product(String name, String desc, Double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Product() {

    }
}
