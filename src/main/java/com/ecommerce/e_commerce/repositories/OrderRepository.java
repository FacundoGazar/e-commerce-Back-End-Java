package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Orders;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {
    Orders saveOrder(Orders orders);
    List<Orders> findByOrderDateBefore(LocalDate date);
    List<Orders> findByPriceLessThanEqual(Double price);
    List<Orders> findByPriceLessThanEqualAndOrderDateBefore(Double price, LocalDate date);
    List<Orders> getOrders();
    Orders findOrderById(Integer id);
}
