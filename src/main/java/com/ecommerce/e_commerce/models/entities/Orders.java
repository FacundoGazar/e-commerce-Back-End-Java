package com.ecommerce.e_commerce.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    private String state;
    private LocalDate orderDate;

    @Transient
    private Integer userId;

    private Double price;

    public Double getPrice() {
        return orderDetail.stream()
                .mapToDouble(detail ->
                        detail.getProduct().getPrice() * detail.getAmount()
                )
                .sum();
    }

}
