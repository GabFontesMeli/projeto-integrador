package com.example.projetointegrador.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.projetointegrador.exceptions.CartItemNotFoundException;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.repository.CartItemRepository;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest {
    @InjectMocks
    private CartItemService cartItemService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Test
    void discountOnCartItemsCartItemNotFoundException() throws CartItemNotFoundException {
        List<CartItem> cartItemsToExpire = new ArrayList<>();
        
        BDDMockito.given(cartItemRepository.findAllCartItemsByBatchProductExpirationDateLessThanEqual(any(LocalDate.class))).willReturn(cartItemsToExpire);

        assertThrows(CartItemNotFoundException.class, () -> cartItemService.discountOnCartItems(2.00));
    }
}
