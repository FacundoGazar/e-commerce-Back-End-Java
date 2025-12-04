package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Orders;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
    private final OrderRepositoryJPA orderRepository;

    public OrderRepositoryImpl(OrderRepositoryJPA orderRepository){
        this.orderRepository = orderRepository;
    }

    public Orders saveOrder(Orders orders){
        return this.orderRepository.save(orders);
    }

    public List<Orders> findByOrderDateBefore(LocalDate date){
        return this.orderRepository.findByOrderDateBefore(date);
    }

    public List<Orders> findByPriceLessThanEqual(Double price){
        return this.orderRepository.findByPriceLessThanEqual(price);
    }
    public List<Orders> findByPriceLessThanEqualAndOrderDateBefore(Double price, LocalDate date){
        return this.orderRepository.findByPriceLessThanEqualAndOrderDateBefore(price, date);
    }

    public List<Orders> getOrders(){
        return this.orderRepository.findAll();
    }

    public Orders findOrderById(Integer id){
        return this.orderRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontró un pedido con ese ID"));
    }
}
