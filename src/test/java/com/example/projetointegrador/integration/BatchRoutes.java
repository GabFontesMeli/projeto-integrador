package com.example.projetointegrador.integration;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.BatchProduct;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class BatchRoutes extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createBatchShouldReturnBatch() throws Exception {

        final String path = "src/test/java/com/example/projetointegrador/mocks/requestsBody/createBatchPayload.json";

        BatchDTO mock = objectMapper.readValue(new File(path), BatchDTO.class);
        String payload = objectMapper.writeValueAsString(mock);

        this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.storage.id").value(1))
                .andExpect(jsonPath("$.storage.volume").value(100000.0))
                .andExpect(jsonPath("$.storage.users").isEmpty())
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].id").value(4))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].quantity").value(12))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].manufacturingDate").value("2022-12-10"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].expirationDate").value("2023-01-01"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].section.id").value(1))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].remainingQuantity").value(12));
    }

    @Test
    @Order(2)
    void updateBatchShouldReturnBatch() throws Exception {
//        // response
//        final String responsePath = "src/test/java/com/example/projetointegrador/mocks/responsesBody/updateBatchResponse.json";
//        Batch response = objectMapper.readValue(new File(responsePath), Batch.class);
//        String responseAsString = objectMapper.writeValueAsString(response);

        // payload
        final String payloadPath = "src/test/java/com/example/projetointegrador/mocks/requestsBody/updateBatchPayload.json";
        List<BatchProduct> request = Arrays.asList(objectMapper.readValue(new File(payloadPath), BatchProduct[].class));
        String payload = objectMapper.writeValueAsString(request);

        this.mockMvc
                .perform(
                        patch("/api/v1/batch/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.storage.id").value(1))
                .andExpect(jsonPath("$.storage.volume").value(100000.0))
                .andExpect(jsonPath("$.storage.users").isEmpty())
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].id").value(5))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].product.id").value(4))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].product.name").value("carne bovina"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].product.price").value(10.0))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].product.volume").value(7.0))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].product.users[0]").isEmpty())
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].quantity").value(20))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].manufacturingDate").value("2022-12-23"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].section.id").value(3))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 5)].remainingQuantity").value(20))
                .andExpect(jsonPath("$.batchProduct", hasSize(4)));
    }
}
