package com.example.projetointegrador.service;

import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.SectionRepository;
import com.example.projetointegrador.repository.StorageRepository;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private SectionRepository sectionRepository;

    @Mock
    private StorageRepository storageRepository;


    @Test
    void createBatchShouldReturnBatch() {
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);
        BDDMockito.given(sectionRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(section));
        BDDMockito.given(storageRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(storage));

        Batch response = null;
        try {
            response = batchService.createBatch(batchDTO);
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
