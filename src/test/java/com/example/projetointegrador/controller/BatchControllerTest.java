package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
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

import java.io.File;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BatchController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BatchControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchService batchService;

    @Test
    void createBatchShouldReturnBatch() throws Exception {

        Batch returningBatch = objectMapper.readValue(new File(path + "/responsesBody/createBatchResponse.json"), Batch.class);

        BDDMockito.given(batchService.createBatch(any(BatchDTO.class)))
                .willReturn(returningBatch);

        BatchDTO payload = objectMapper.readValue(new File(path + "/requestsBody/createBatchPayload.json"), BatchDTO.class);

        this.mockMvc
                .perform(
                        post("/api/v1/batch")
                                .content(objectMapper.writeValueAsString(payload))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(returningBatch)));
        }

    @Test
    void updateBatchShouldReturnBatch() throws Exception {

        Batch returningBatch = objectMapper.readValue(new File(path + "/responsesBody/updateBatchResponse.json"), Batch.class);

        BDDMockito.given(batchService.update(any(Long.class), any(Set.class)))
                .willReturn(returningBatch);

        Set<BatchProduct> payload = objectMapper.readValue(new File(path + "/requestsBody/updateBatchPayload.json"), Set.class);

        this.mockMvc
                .perform(
                        put("/api/v1/batch/1")
                                .content(objectMapper.writeValueAsString(payload))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(content().json(objectMapper.writeValueAsString(returningBatch)));
    }
}
