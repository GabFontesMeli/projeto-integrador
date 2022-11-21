package com.example.projetointegrador.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BatchProductTest {

    @Test
    public void test(){
        Long id = 100L;
        Batch batch = new Batch();
        Product product = new Product();
        Integer quantity = 50;
        LocalDate manufacturingDate = LocalDate.now();
        LocalDate expirationDate = LocalDate.now();
        Section section = new Section();
        Integer remainingQuantity = 10;

        BatchProduct batchProduct = new BatchProduct();
        batchProduct.setId(id);
        batchProduct.setProduct(product);
        batchProduct.setBatch(batch);
        batchProduct.setQuantity(quantity);
        batchProduct.setManufacturingDate(manufacturingDate);
        batchProduct.setExpirationDate(expirationDate);
        batchProduct.setSection(section);
        batchProduct.setRemainingQuantity(remainingQuantity);

        assertThat(batchProduct.getId()).isEqualTo(id);
        assertThat(batchProduct.getProduct()).isEqualTo(product);
        assertThat(batchProduct.getBatch()).isEqualTo(batch);
        assertThat(batchProduct.getRemainingQuantity()).isEqualTo(remainingQuantity);
        assertThat(batchProduct.getQuantity()).isEqualTo(quantity);
        assertThat(batchProduct.getManufacturingDate()).isEqualTo(manufacturingDate);
        assertThat(batchProduct.getExpirationDate()).isEqualTo(expirationDate);
        assertThat(batchProduct.getSection()).isEqualTo(section);

    }
}
