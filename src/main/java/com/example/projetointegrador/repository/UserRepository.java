package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.UserU;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserU, Long> {

    Optional<UserU> findByName(String name);

    UserU findByNameAndSecretPassword(String userName, String password);
}
