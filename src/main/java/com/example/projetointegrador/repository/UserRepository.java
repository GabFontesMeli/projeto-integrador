package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.UserU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserU, Long> {

    Optional<UserU> findByName(String name);

    Boolean existsByName(String name);

    UserU findByNameAndSecretPassword(String userName, String password);
    UserU findUserUById(Long userId);
}
