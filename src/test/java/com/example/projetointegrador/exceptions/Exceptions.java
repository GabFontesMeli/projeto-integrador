package com.example.projetointegrador.exceptions;

import com.example.projetointegrador.controller.BatchController;
import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.service.BatchService;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BatchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class Exceptions extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchService batchService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void updateBatchShouldReturnBatchInvalidException() throws Exception {

        BDDMockito.given(batchService.update(any(Long.class), any(Set.class)))
                .willThrow(new BatchInvalidException("Batch doesn't exists"));

        String payload = objectMapper.writeValueAsString(batchProductsPayload);

        this.mockMvc
                .perform(
                        put("/api/v1/batch/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("batch invalid"))
                .andExpect(jsonPath("$.message").value("Batch doesn't exists"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty());
    }

    @Test
    void createBatchShouldReturnSectionInvalidException() throws Exception {

        BDDMockito.given(batchService.createBatch(any(BatchDTO.class)))
                .willThrow(new SectionInvalidException("section not found"));

        String payload = objectMapper.writeValueAsString(batchDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("section invalid"))
                .andExpect(jsonPath("$.message").value("section not found"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timeStamp").isNotEmpty());
    }
}
