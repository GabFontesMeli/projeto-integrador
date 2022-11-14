package com.example.projetointegrador.service.interfaces;

import java.util.List;

import com.example.projetointegrador.model.CartItem;

public interface ICartItemService {
    List<CartItem> getCartItems(Long cartId);
}