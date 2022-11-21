package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.ProductInBatchDTO;
import com.example.projetointegrador.service.BatchProductService;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

@WebMvcTest(BatchProductController.class)
public class BatchProductControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchProductService batchProductService;

    @Test
    public void findAllByProductIdShouldReturnProductInBatchDTO() throws Exception {

        ProductInBatchDTO response = objectMapper.readValue(
                new File(path + "/responsesBody/BatchProduct/findBatchProductsByProductIdResponse.json"),
                ProductInBatchDTO.class);

        BDDMockito.given(batchProductService.findBatchProductsByProductId(any(Long.class))).willReturn(response);

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/list/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}
