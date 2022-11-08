package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Long> {
}
