package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findCartByUserId(Long userId);
}
