package com.example.projetointegrador.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class StorageDTOTest {

    @Test
    public void test() {
        Long storageId = 1L;
        Integer quantity = 10;

        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setStorageId(storageId);
        storageDTO.setQuantity(quantity);

        assertThat(storageDTO).isNotNull();
        assertThat(storageDTO.getStorageId()).isEqualTo(storageId);
        assertThat(storageDTO.getQuantity()).isEqualTo(quantity);
    }
}
