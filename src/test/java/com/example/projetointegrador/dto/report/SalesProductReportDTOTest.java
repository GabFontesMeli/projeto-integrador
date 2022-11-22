package com.example.projetointegrador.dto.report;

import com.example.projetointegrador.dto.CartStatusDTO;
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
public class SalesProductReportDTOTest {

    @Test
    public void test() {

        String salesProductReport = "salesProductReport";
        String user = "user";
        Integer totalProduct = 1;
        List<SalesProductReportListDTO> products = new ArrayList<SalesProductReportListDTO>();

        SalesProductReportDTO salesProductReportDTO = SalesProductReportDTO.builder()
                .salesProductReport(salesProductReport)
                .user(user)
                .totalProduct(totalProduct)
                .products(products)
                .build();

        assertThat(salesProductReportDTO).isNotNull();
        assertThat(salesProductReportDTO.getSalesProductReport()).isEqualTo(salesProductReport);
        assertThat(salesProductReportDTO.getUser()).isEqualTo(user);
        assertThat(salesProductReportDTO.getTotalProduct()).isEqualTo(totalProduct);
        assertThat(salesProductReportDTO.getProducts()).isEqualTo(products);

    }
}
