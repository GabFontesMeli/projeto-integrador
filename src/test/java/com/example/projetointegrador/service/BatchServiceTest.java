package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.exceptions.ProductNotFoundException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.exceptions.StorageInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest extends BaseTest {

    @InjectMocks
    private BatchService batchService;

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private StorageService storageService;

    @Mock
    private ProductService productService;

    @Mock
    private SectionService sectionService;

    @Mock
    private StorageRepository storageRepository;


    @Test
    void createBatchShouldReturnBatch() throws StorageInvalidException, SectionInvalidException, ProductNotFoundException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        final String path = "src/test/java/com/example/projetointegrador/mocks/requestsBody/createBatchPayload.json";

        BatchDTO mock = objectMapper.readValue(new File(path), BatchDTO.class);

        BDDMockito.given(storageService.findById(any(Long.class))).willReturn(storage);
        BDDMockito.given(productService.findById(any(Long.class))).willReturn(product);
        BDDMockito.given(sectionService.findById(any(Long.class))).willReturn(section);
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);

        Batch response = null;
        try {
            response = batchService.createBatch(mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(response).isEqualTo(batch);
    }

    @Test
    void updateBatchShouldReturnBatch() {
        BDDMockito.given(batchRepository.existsById(any(Long.class))).willReturn(true);
        BDDMockito.given(batchRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(batch));
        batch.addProducts(batchProductsPayload);
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);

        Batch response = null;
        try {
            response = batchService.update(1L, batchProductsPayload);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(response).isEqualTo(batch);
    }
}
