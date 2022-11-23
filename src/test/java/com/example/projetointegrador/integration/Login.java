package com.example.projetointegrador.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class Login {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginShouldReturnToken() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/v1/login")
                                .contentType("application/json")
                                .content("{\"name\": \"teste\", \"secretPassword\": \"123456\"}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void wrongLoginShouldReturnError() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/v1/login")
                                .contentType("application/json")
                                .content("{\"name\": \"teste\", \"secretPassword\": \"1234567\"}")
                )
                .andExpect(status().isConflict());
    }
}
