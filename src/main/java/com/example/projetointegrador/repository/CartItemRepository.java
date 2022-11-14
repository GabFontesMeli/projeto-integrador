package com.example.projetointegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetointegrador.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    List<CartItem> findAllCartItemsByCartId(Long cartId);
}