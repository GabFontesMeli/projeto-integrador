package com.example.projetointegrador.dto.report;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SalesProductReportListDTOTest {

    @Test
    public void test() {

        String productName = "banana";
        BigInteger quantity = new BigInteger("1");
        Object[] obj = new Object[]{productName, quantity};
        SalesProductReportListDTO salesProductReportDTO = new SalesProductReportListDTO(obj);

        assertThat(salesProductReportDTO).isNotNull();
        assertThat(salesProductReportDTO.getProductName()).isEqualTo(productName);
        assertThat(salesProductReportDTO.getQuantity()).isEqualTo(quantity.intValue());
    }
}
