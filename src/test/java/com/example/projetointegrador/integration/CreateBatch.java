package com.example.projetointegrador.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.aspectj.lang.annotation.Before;

import com.example.projetointegrador.repository.ProductRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
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
        productRepository.save(productForTest);
        storageRepository.save(storage);
        sectionRepository.save(section);
    }

    // @AfterEach
    // void databaseCleanup() {
    //     EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetointegrador_test");
    //     EntityManager em = emf.createEntityManager();
    //     em.clear();
    // }

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
