package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJPA userRepository;

    public UserRepositoryImpl(UserRepositoryJPA userRepository) {
        this.userRepository = userRepository;
    }

    public Users saveUser(Users users) {
        return this.userRepository.save(users);
    }

    public List<Users> findAll() {
        return this.userRepository.findAll();
    }

    public Users findUserById(Integer id) {
        return this.userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("No se encontró a un usuario con ese ID"));
    }
}
