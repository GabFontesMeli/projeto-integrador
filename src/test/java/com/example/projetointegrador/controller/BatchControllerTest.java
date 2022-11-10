package com.example.projetointegrador.controller;

import com.example.projetointegrador.controller.BatchController;
import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.*;
import com.example.projetointegrador.service.BatchService;
import com.example.projetointegrador.setup.BaseTest;
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
public class BatchControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchService batchService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBatchshouldReturnBatch() throws Exception {
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
