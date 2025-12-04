package com.ecommerce.e_commerce.services;

import com.ecommerce.e_commerce.models.entities.Orders;
import com.ecommerce.e_commerce.models.entities.Users;
import com.ecommerce.e_commerce.repositories.UserRepository;
import com.ecommerce.e_commerce.repositories.UserRepositoryImpl;
import com.ecommerce.e_commerce.repositories.UserRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    public UserRepositoryJPA repository;

    public UserServiceImpl(UserRepositoryJPA repository){
        this.repository = repository;
    }

    public Users createUser(Users users){
        return this.repository.save(users);
    }

    public List<Orders> listOrders(Integer id){
        Users users = this.repository.findById(id).orElseThrow(() -> new RuntimeException("jalo"));

        return users.getOrders();
    }

    public List<Users> listUsers(){
        return this.repository.findAll();
    }
}
