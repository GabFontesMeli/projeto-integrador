package com.example.projetointegrador.controller;

import com.example.projetointegrador.controller.BatchController;
import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.service.BatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ActiveProfiles("test")
@WebMvcTest(BatchController.class)
public class BatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchService batchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBatchshouldReturnBatch() throws Exception {
        Batch batch = new Batch();
        batch.setId(3L);
        batch.setExpirationDate(LocalDate.parse("2023-01-01"));

        Section section = new Section();
        section.setId(1L);
        section.setName("teste1");
        section.setTemperature(3.1f);

        Set<BatchProduct> batchProducts = new HashSet<>();
        BatchProduct batchProduct = new BatchProduct();
        batchProduct.setId(1L);
        batchProduct.setQuantity(10);
        batchProduct.setManufacturingDate(LocalDate.parse("2022-12-10"));

        batchProducts.add(batchProduct);

        Storage storage = new Storage();
        storage.setId(1L);
        storage.setVolume(100.0f);

        batch.setSection(section);
        batch.setStorage(storage);
        batch.setBatchProduct(batchProducts);

        BDDMockito.given(batchService.createBatch(any(BatchDTO.class)))
                .willReturn(batch);

        String jsonExpected = objectMapper.writeValueAsString(batch);

       this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(jsonExpected)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonExpected));
    }
}
