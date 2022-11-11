package com.example.projetointegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
    
}
