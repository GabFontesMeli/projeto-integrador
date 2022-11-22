package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.UserU;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserU, Long> {

    UserU findUserUById(Long userId);
}
