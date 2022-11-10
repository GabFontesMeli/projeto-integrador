package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.Inventory;
import com.example.projetointegrador.model.Section;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willReturn;

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
    void createBatchShoulReturnBatch() {
        BDDMockito.given(batchRepository.save(any(Batch.class))).willReturn(batch);
        BDDMockito.doNothing().when(inventoryService).saveInventory(any(Inventory.class));
        BDDMockito.given(sectionRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(section));
        BDDMockito.given(storageRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(storage));

        Batch response = batchService.createBatch(batchDTO);

        assertThat(response).isEqualTo(batch);
    }
}
