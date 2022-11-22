package com.example.projetointegrador.service.interfaces;

import com.example.projetointegrador.dto.CartDTO;
import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.exceptions.*;

public interface ICartService {
    Double createCart(CartDTO cartDTO) throws UserUNotFoundException, InsufficientStockException, ProductNotFoundException, ExpiredProductException;
    String changeCartStatus(Long cartId, CartStatusDTO cartStatusDTO);
    CartStatusDTO cancelOrder(Long cartId, Long userId) throws CartNotFoundException, InvalidUserException, ExpiredCancellationPeriodException, UnfinishedOrderException;
}
