package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.CartItemNotFoundException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.service.BatchService;
import com.example.projetointegrador.service.CartItemService;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartItemController.class)
public class CartItemControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartItemService cartService;

    @Test
    void getCartItemShouldReturnCartItem() throws Exception {

        List<CartItem> cartItems = Arrays.asList(objectMapper.readValue(new File(path + "/responsesBody/getCartItemResponse.json"), CartItem.class));

        BDDMockito.given(cartService.getCartItems(any(Long.class)))
                .willReturn(cartItems);

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/1")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cartItems)));
    }

    @Test
    void discountOnCartItemsShouldReturnMessage() throws Exception {
        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();
        
        List<CartItem> cartItemToExpire = Arrays.asList(mp.readValue(new File(path + "/responsesBody/CartItem/getCartItemsResponse.json"), CartItem[].class));

        List<CartItem> cartItem = new ArrayList<>();
        cartItem.add(cartItemToExpire.get(1));

        BDDMockito.given(cartService.discountOnCartItems(any(Double.class)))
                .willReturn("Discount on products in CartItem");

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/discount/2")
                )
                .andExpect(status().isOk());
        }
}

