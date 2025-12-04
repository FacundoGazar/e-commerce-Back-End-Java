package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.request.OrdersRequest;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    Orders saveOrder(OrdersRequest request);

    List<Orders> listOrders(Double price, LocalDate date);

    Orders viewOrderDetail(Integer id);

    Orders nextStateOrder(Integer id);
}
