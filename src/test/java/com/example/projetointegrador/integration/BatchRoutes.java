package com.example.projetointegrador.integration;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.BatchProductNotFoundException;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.BatchProduct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

//@Sql(scripts = "integration.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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

        BatchDTO mock = objectMapper.readValue(new File(path + "/requestsBody/createBatchPayload.json"), BatchDTO.class);
        String payload = objectMapper.writeValueAsString(mock);

        this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.storage.id").value(1))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].id").value(6))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].quantity").value(12))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].manufacturingDate").value("2022-12-10"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].expirationDate").value("2023-01-01"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].section.id").value(1))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 6)].remainingQuantity").value(12));
    }

    @Test
    @Order(2)
    void updateBatchShouldReturnBatch() throws Exception {
//        // response
//        final String responsePath = "src/test/java/com/example/projetointegrador/mocks/responsesBody/updateBatchResponseIntegration.json";
//        Batch response = objectMapper.readValue(new File(responsePath), Batch.class);
//        String responseAsString = objectMapper.writeValueAsString(response);

        // payload
        List<BatchProduct> request = Arrays.asList(objectMapper.readValue(new File(path + "/requestsBody/updateBatchPayload.json"), BatchProduct[].class));
        String payload = objectMapper.writeValueAsString(request);

        this.mockMvc
                .perform(
                        put("/api/v1/batch/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.storage.id").value(1))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].id").value(4))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].quantity").value(20))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].manufacturingDate").value("2022-12-23"))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].section.id").value(1))
                .andExpect(jsonPath("$.batchProduct[?(@.id == 4)].remainingQuantity").value(20))
                .andExpect(jsonPath("$.batchProduct", hasSize(3)));
    }
    @Test
    void updateBatchShouldReturnBatchProductNotFound() throws Exception {
        List<BatchProduct> request = Arrays.asList(objectMapper.readValue(new File(path + "/requestsBody/updateBatchExceptionPayload.json"), BatchProduct[].class));
        String payload = objectMapper.writeValueAsString(request);

        this.mockMvc
                .perform(
                        put("/api/v1/batch/2")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BatchProductNotFoundException));

    }
}
