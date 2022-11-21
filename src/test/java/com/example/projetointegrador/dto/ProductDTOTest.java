package com.example.projetointegrador.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductDTOTest {

    @Test
    public void test() {
        Long productId = 1L;
        List<StorageDTO> storages = new ArrayList<StorageDTO>();

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productId);
        productDTO.setStorages(storages);

        assertThat(productDTO).isNotNull();
        assertThat(productDTO.getProductId()).isEqualTo(productId);
        assertThat(productDTO.getStorages()).isEqualTo(storages);
    }
}
