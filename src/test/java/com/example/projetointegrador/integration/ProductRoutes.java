package com.example.projetointegrador.integration;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.dto.ProductDTO;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRoutes extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveProductShouldReturnProduct() throws Exception {

        Product responseJson = objectMapper.readValue(new File(path + "/responsesBody/Product/saveProductResponse.json"), Product.class);
        Product requestJson = objectMapper.readValue(new File(path + "/requestsBody/Product/saveProductRequest.json"), Product.class);
        String payload = objectMapper.writeValueAsString(requestJson);
        String response = objectMapper.writeValueAsString(responseJson);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(response));
    }
}
