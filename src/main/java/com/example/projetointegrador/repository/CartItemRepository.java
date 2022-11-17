package com.example.projetointegrador.repository;

import com.example.projetointegrador.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    List<CartItem> findAllCartItemsByCartId(Long cartId);
}