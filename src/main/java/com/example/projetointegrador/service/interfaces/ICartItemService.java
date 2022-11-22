package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.exceptions.CartItemNotFoundException;
import com.example.projetointegrador.model.CartItem;

import java.util.List;

public interface ICartItemService {
    List<CartItem> getCartItems(Long cartId);
    String discountOnCartItems(Double discount) throws CartItemNotFoundException;
}