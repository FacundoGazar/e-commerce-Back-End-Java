package com.ecommerce.e_commerce.repositories;

import com.ecommerce.e_commerce.models.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA extends JpaRepository<Users, Integer> {
}
