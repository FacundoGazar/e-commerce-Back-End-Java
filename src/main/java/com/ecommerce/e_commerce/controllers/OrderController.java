package com.ecommerce.e_commerce.controllers;

import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.request.OrdersRequest;
import com.ecommerce.e_commerce.services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Orders createOrder(@RequestBody OrdersRequest request) {
        return this.service.saveOrder(request);
    }

    @GetMapping
    public List<Orders> listOrders(@RequestParam(required = false) Double price,
                                   @RequestParam(required = false) LocalDate orderDate) {
        return this.service.listOrders(price, orderDate);
    }

    @GetMapping("/{id}")
    public Orders viewOrderDetail(@PathVariable Integer id) {
        return this.service.viewOrderDetail(id);
    }

    @PutMapping("/{id}")
    public Orders nextStateOrder(@PathVariable Integer id) {
        return this.service.nextStateOrder(id);
    }
}
