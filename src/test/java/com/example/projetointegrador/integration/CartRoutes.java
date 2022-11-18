package com.example.projetointegrador.integration;

import java.io.File;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projetointegrador.dto.CartDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CartRoutes {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String path = "src/test/java/com/example/projetointegrador/utils";

    @Test
    @Order(1)
    void createCartShouldReturnDouble() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCart(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(16.0));
    }
}
