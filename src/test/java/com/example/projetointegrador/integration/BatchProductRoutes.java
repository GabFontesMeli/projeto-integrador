package com.example.projetointegrador.integration;

import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BatchProductRoutes extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findBatchProductsByProductIdShouldReturnProductInBatchDTO() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/list/{productId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.section.id").value(1))
                .andExpect(jsonPath("$.section.storageId").value(1))
                .andExpect(jsonPath("$.batchProducts", hasSize(3)))
                .andExpect(jsonPath("$.batchProducts[0].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[0].remainingQuantity").value(100))
                .andExpect(jsonPath("$.batchProducts[0].expirationDate").value("2024-12-01"));
    }

    @Test
    public void findBatchProductsByProductIdOrderedByRemainingQuantityShouldReturnProductInBatchDTO() throws Exception {
        String orderTypeByRemainingQuantity = "Q";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/list/{productId}/{order}", 1, orderTypeByRemainingQuantity)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.section.id").value(1))
                .andExpect(jsonPath("$.section.storageId").value(1))
                .andExpect(jsonPath("$.batchProducts", hasSize(3)))
                .andExpect(jsonPath("$.batchProducts[0].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[0].remainingQuantity").value(20))
                .andExpect(jsonPath("$.batchProducts[0].expirationDate").value("2025-01-01"))
                .andExpect(jsonPath("$.batchProducts[1].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[1].remainingQuantity").value(50))
                .andExpect(jsonPath("$.batchProducts[1].expirationDate").value("2026-01-01"))
                .andExpect(jsonPath("$.batchProducts[2].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[2].remainingQuantity").value(100))
                .andExpect(jsonPath("$.batchProducts[2].expirationDate").value("2024-12-01"));
    }

    @Test
    public void findBatchProductsByProductIdOrderedByExpirationDateShouldReturnProductInBatchDTO() throws Exception {
        String orderTypeByExpirationDate = "V";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/list/{productId}/{order}", 1, orderTypeByExpirationDate)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.section.id").value(1))
                .andExpect(jsonPath("$.section.storageId").value(1))
                .andExpect(jsonPath("$.batchProducts", hasSize(3)))
                .andExpect(jsonPath("$.batchProducts[0].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[0].remainingQuantity").value(100))
                .andExpect(jsonPath("$.batchProducts[0].expirationDate").value("2024-12-01"))
                .andExpect(jsonPath("$.batchProducts[1].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[1].remainingQuantity").value(20))
                .andExpect(jsonPath("$.batchProducts[1].expirationDate").value("2025-01-01"))
                .andExpect(jsonPath("$.batchProducts[2].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[2].remainingQuantity").value(50))
                .andExpect(jsonPath("$.batchProducts[2].expirationDate").value("2026-01-01"));
    }

    @Test
    public void findBatchProductsByProductIdOrderedByBatchIdShouldReturnProductInBatchDTO() throws Exception {
        String orderTypeByBatchId = "L";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/list/{productId}/{order}", 1, orderTypeByBatchId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.section.id").value(1))
                .andExpect(jsonPath("$.section.storageId").value(1))
                .andExpect(jsonPath("$.batchProducts", hasSize(3)))
                .andExpect(jsonPath("$.batchProducts[0].batchId").value(1))
                .andExpect(jsonPath("$.batchProducts[0].remainingQuantity").value(20))
                .andExpect(jsonPath("$.batchProducts[0].expirationDate").value("2025-01-01"))
                .andExpect(jsonPath("$.batchProducts[1].batchId").value(2))
                .andExpect(jsonPath("$.batchProducts[1].remainingQuantity").value(50))
                .andExpect(jsonPath("$.batchProducts[1].expirationDate").value("2026-01-01"))
                .andExpect(jsonPath("$.batchProducts[2].batchId").value(3))
                .andExpect(jsonPath("$.batchProducts[2].remainingQuantity").value(100))
                .andExpect(jsonPath("$.batchProducts[2].expirationDate").value("2024-12-01"));
    }
}
