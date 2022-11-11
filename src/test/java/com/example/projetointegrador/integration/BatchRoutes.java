package com.example.projetointegrador.integration;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.repository.BatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
// @DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BatchRoutes extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @BeforeEach
    void databaseSetup() {
        storageRepository.save(storage);
        sectionRepository.save(section);
        productRepository.save(productForTest);
    }

    @Test
    void createBatchShouldReturnBatch() throws Exception {
        String payload = objectMapper.writeValueAsString(batchDTO);
        String jsonExpected = objectMapper.writeValueAsString(batch);

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
        Set<BatchProduct> newBatchProducts = batchProductsBuilder2(productForTest, batch);

        batch.setBatchProduct(newBatchProducts);
        batchRepository.save(batch);

        Product product = new Product();
        product.setName("teste");
        product.setVolume(10.0f);
        product.setPrice(10.0d);
        productRepository.save(product);

        Set<BatchProduct> batchProducts = batchProductsBuilder(product);
        String payload = objectMapper.writeValueAsString(batchProducts);

        batch.addProducts(batchProducts);

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
