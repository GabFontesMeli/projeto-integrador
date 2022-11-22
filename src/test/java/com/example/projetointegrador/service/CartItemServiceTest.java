package com.example.projetointegrador.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest extends BaseTest {
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

    @Test
    void discountOnCartItems() throws CartItemNotFoundException, StreamReadException, DatabindException, IOException {
        
        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();
        
        List<CartItem> cartItemToExpire = Arrays.asList(mp.readValue(new File(path + "/responsesBody/CartItem/getCartItemsResponse.json"), CartItem[].class));

        List<CartItem> cartItem = new ArrayList<>();
        cartItem.add(cartItemToExpire.get(1));

        BDDMockito.given(cartItemRepository.findAllCartItemsByBatchProductExpirationDateLessThanEqual(any(LocalDate.class))).willReturn(cartItem);

        assertAll("Discount on products in CartItem",  () -> cartItemService.discountOnCartItems(2.00));
    }
}
