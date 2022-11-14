package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartStatusDTO;

public interface ICartService {
    Double createCart(CartDTO cartDTO);
    String changeCartStatus(Long cartId, CartStatusDTO cartStatusDTO);
}
