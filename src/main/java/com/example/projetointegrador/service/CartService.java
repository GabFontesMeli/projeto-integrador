package com.example.projetointegrador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.service.interfaces.ICartService;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Double createCart(CartDTO cartDTO) {
        
        Cart cart = new Cart(cartDTO);
        return null;
    }  

    
}
