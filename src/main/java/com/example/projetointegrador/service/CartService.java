package com.example.projetointegrador.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.service.interfaces.ICartService;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Double createCart(CartDTO cartDTO) {
        
        Cart cart = new Cart(cartDTO);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.addAll(cart.getCartItems());

        cart.addCartItems(cartItems);

        cartRepository.save(cart);

        Double totalValue = cart.getTotalValue();

        return totalValue;
    }  
}
