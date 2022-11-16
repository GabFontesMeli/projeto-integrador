package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.service.BatchService;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatchController.class)
public class BatchControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchService batchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBatchShouldReturnBatch() throws Exception {

        BDDMockito.given(batchService.createBatch(any(BatchDTO.class)))
                .willReturn(batch);

        String payload = objectMapper.writeValueAsString(batchDTO);
        String jsonExpected = objectMapper.writeValueAsString(batch);

        System.out.println(payload);

       this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonExpected));
    }

    @Test
    void updateBatchShouldReturnBatch() throws Exception {

        BDDMockito.given(batchService.update(any(Long.class), any(Set.class)))
                .willReturn(batch);

        String payload = objectMapper.writeValueAsString(batchProductsPayload);
        String jsonExpected = objectMapper.writeValueAsString(batch);

       this.mockMvc
                .perform(
                        patch("/api/v1/batch/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(content().json(jsonExpected));
    }
}
