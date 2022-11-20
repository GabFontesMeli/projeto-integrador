package com.example.projetointegrador.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BatchTest {

    @Test
    public void test(){
        Long id = 100L;
        Storage storage = new Storage();
        Set<BatchProduct> batchProduct = new HashSet<>();

        Batch batch = new Batch();
        batch.setId(id);
        batch.setStorage(storage);
        batch.setBatchProduct(batchProduct);

        assertThat(batch.getId()).isEqualTo(id);
        assertThat(batch.getStorage()).isEqualTo(storage);
        assertThat(batch.getBatchProduct()).isEqualTo(batchProduct);
    }
}
