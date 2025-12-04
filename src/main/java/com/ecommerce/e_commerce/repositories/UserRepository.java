package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    Users saveUser(Users users);
    List<Users> findAll();
    Users findUserById(Integer id);
}
