package com.example.projetointegrador.service;

import com.example.projetointegrador.exceptions.BatchInvalidException;
import com.example.projetointegrador.exceptions.SectionInvalidException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.repository.BatchRepository;
import com.example.projetointegrador.repository.InventoryRepository;
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
    private InventoryRepository inventoryRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private StorageRepository storageRepository;

    @Mock
    private InventoryService inventoryService;

    @Test
    void createBatchShouldReturnBatch() {
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);
        BDDMockito.doNothing().when(inventoryService).saveInventory(any(Inventory.class));
        BDDMockito.given(sectionRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(section));
        BDDMockito.given(storageRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(storage));

        Batch response = null;
        try {
            response = batchService.createBatch(batchDTO);
        } catch (SectionInvalidException e) {
            throw new RuntimeException(e);
        }

        assertThat(response).isEqualTo(batch);
    }

    @Test
    void updateBatchShouldReturnBatch() {
        BDDMockito.given(batchRepository.existsById(any(Long.class))).willReturn(true);
        BDDMockito.given(batchRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(batch));
        BDDMockito.doNothing().when(inventoryService).saveInventory(any(Inventory.class));
        batch.addProducts(batchProductsPayload);
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);

        Batch response = null;
        try {
            response = batchService.update(1L, batchProductsPayload);
        } catch (BatchInvalidException e) {
            throw new RuntimeException(e);
        }

        assertThat(response).isEqualTo(batch);
    }
}
