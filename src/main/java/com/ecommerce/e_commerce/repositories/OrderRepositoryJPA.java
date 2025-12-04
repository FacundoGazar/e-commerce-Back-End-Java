package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepositoryJPA extends JpaRepository<Orders, Integer> {
    List<Orders> findByOrderDateBefore(LocalDate date);
    List<Orders> findByPriceLessThanEqual(Double price);
    List<Orders> findByPriceLessThanEqualAndOrderDateBefore(Double price, LocalDate date);
}
