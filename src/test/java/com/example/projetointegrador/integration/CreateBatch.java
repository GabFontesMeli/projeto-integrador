package com.example.projetointegrador.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
// @Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
// @DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CreateBatch extends BaseTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
}
