package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.service.BatchService;
import com.example.projetointegrador.service.CartService;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void cancelOrderShouldReturnCartStatusDTO() throws Exception {

        CartStatusDTO returningCartStatusDTO = objectMapper.readValue(new File(path + "/responsesBody/Cart/cancelOrderResponse.json"), CartStatusDTO.class);

        BDDMockito.given(cartService.cancelOrder(any(Long.class), any(Long.class)))
                .willReturn(returningCartStatusDTO);

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/{cartId}/{userId}", 2, 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returningCartStatusDTO)));
    }
}
