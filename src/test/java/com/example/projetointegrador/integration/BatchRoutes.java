package com.example.projetointegrador.integration;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BatchRoutes extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBatchShouldReturnBatch() throws Exception {
        String payload = objectMapper.writeValueAsString(batchDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.expirationDate").value("2023-01-01"))
                .andExpect(jsonPath("$.storage.id").value(1))
                .andExpect(jsonPath("$.storage.volume").value(100000.0))
                .andExpect(jsonPath("$.section.id").value(1))
                .andExpect(jsonPath("$.section.name").value("frescos"))
                .andExpect(jsonPath("$.section.temperature").value(20.0))
                .andExpect(jsonPath("$.batchProduct[0].id").value(3))
                .andExpect(jsonPath("$.batchProduct[0].quantity").value(10))
                .andExpect(jsonPath("$.batchProduct[0].manufacturingDate").value("2022-12-10"))
                .andExpect(jsonPath("$.batchProduct[0].manufacturingTime").value("11:00:00"));
    }

    @Test
    void updateBatchShouldReturnBatch() throws Exception {

        Set<BatchProduct> batchProducts = batchProductsBuilder(productForTest);
        String payload = objectMapper.writeValueAsString(batchProducts);

        this.mockMvc
                .perform(
                        patch("/api/v1/batch/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.batchProduct", hasSize(3)))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 3)].id").value(3))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 3)].quantity").value(10))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 3)].manufacturingDate").value("2022-12-10"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 3)].manufacturingTime").value("11:00:00"));
    }
}
