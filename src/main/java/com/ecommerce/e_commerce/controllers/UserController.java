package com.ecommerce.e_commerce.controllers;

import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.entities.Users;
import com.ecommerce.e_commerce.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public Users createUser(@RequestBody Users users){
        return this.service.createUser(users);
    }

    @GetMapping("/{id}")
    public List<Orders> listOrders(@PathVariable Integer id){
        return this.service.listOrders(id);
    }

    @GetMapping
    public List<Users> listUsers(){
        return this.service.listUsers();
    }
}
