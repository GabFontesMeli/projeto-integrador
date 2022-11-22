package com.example.projetointegrador.integration;

import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.model.CartItem;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemRoutes extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCartItemsShouldReturnCartItemsList() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.batchProduct.id == 1)].batchProduct.id").value(1))
                .andExpect(jsonPath("$.[0].quantity").value(2))
                .andExpect(jsonPath("$.[0].itemValue").value(8.0));
    }

    @Test
    void discountOnCartItemsShouldReturnMessage() throws Exception {
        this.mockMvc
                .perform(
                    put("/api/v1/fresh-products/orders/discount/2")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("Discount on products in CartItem"));
    }
}
