package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.OrderDetail;
import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.entities.Product;
import com.ecommerce.e_commerce.models.entities.Users;
import com.ecommerce.e_commerce.models.request.OrdersRequest;
import com.ecommerce.e_commerce.models.states.ProductState;
import com.ecommerce.e_commerce.models.states.ProductStateFactory;
import com.ecommerce.e_commerce.repositories.OrderRepository;
import com.ecommerce.e_commerce.repositories.ProductRepository;
import com.ecommerce.e_commerce.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository repository, UserRepository userRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Orders saveOrder(OrdersRequest request) {

        Users user = userRepository.findUserById(request.getUserId());

        Orders order = new Orders();
        order.setUser(user);
        order.setState(request.getState());
        order.setOrderDate(LocalDate.now());

        List<OrderDetail> details = request.getOrderDetail().stream().map(d -> {
            Product product = productRepository.getProductById(d.getIdProduct())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderDetail detail = new OrderDetail();
            detail.setProduct(product);
            detail.setAmount(d.getAmount());
            return detail;
        }).collect(Collectors.toList());

        order.setOrderDetail(details);

        return repository.saveOrder(order);
    }

    public List<Orders> listOrders(Double price, LocalDate date) {
        if ((price != null && price > 0) && (date != null && date.isBefore(LocalDate.now()))) {
            return this.repository.findByPriceLessThanEqualAndOrderDateBefore(price, date);
        } else if (price != null && price > 0) {
            return this.repository.findByPriceLessThanEqual(price);
        } else if (date != null && date.isBefore(LocalDate.now())) {
            return this.repository.findByOrderDateBefore(date);
        }

        return this.repository.getOrders();
    }

    public Orders viewOrderDetail(Integer id) {
        return this.repository.findOrderById(id);
    }

    public Orders nextStateOrder(Integer id) {
        Orders orders = this.repository.findOrderById(id);
        ProductState current = ProductStateFactory.fromString(orders.getState());
        current.nextState(orders);
        return repository.saveOrder(orders);
    }

}
