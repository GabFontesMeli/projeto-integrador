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
public class ReportBatchProductDTOTest {

    @Test
    public void test() {
        List<ReportProductDTO> products = new ArrayList<>();
        ReportBatchProductDTO batchProduct = new ReportBatchProductDTO();

        batchProduct.setProducts(products);

        assertThat(batchProduct).isNotNull();
        assertThat(batchProduct.getProducts()).isEqualTo(products);
    }
}
