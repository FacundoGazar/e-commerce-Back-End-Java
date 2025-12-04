package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.entities.Users;

import java.util.List;

public interface UserService {
    Users createUser(Users users);
    List<Orders> listOrders(Integer id);
    List<Users> listUsers();
}
