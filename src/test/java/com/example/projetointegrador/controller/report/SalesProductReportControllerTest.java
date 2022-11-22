package com.example.projetointegrador.controller.report;

import com.example.projetointegrador.dto.report.SalesProductReportDTO;
import com.example.projetointegrador.service.report.SalesProductReportService;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SalesProductReportController.class)
@AutoConfigureMockMvc(addFilters = false)
public class SalesProductReportControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesProductReportService salesReportService;

    @Test
    void getReportShouldReturnReportDate() throws Exception {

        SalesProductReportDTO returningReport = objectMapper.readValue(new File(path + "/responsesBody/report/SalesProductReportResponse.json"), SalesProductReportDTO.class);

        BDDMockito.given(salesReportService.getSalesProductReportByPeriod(any(LocalDate.class),any(LocalDate.class)))
                .willReturn(returningReport);

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report?start=1990-01-01&end=2050-01-01")

                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returningReport)));
    }
    @Test
    void getReportShouldReturnReportUserDate() throws Exception {

        SalesProductReportDTO returningReport = objectMapper.readValue(new File(path + "/responsesBody/report/SalesProductReportUserResponse.json"), SalesProductReportDTO.class);

        BDDMockito.given(salesReportService.getSalesProductReportByUserPeriod(any(LocalDate.class),any(LocalDate.class),any(Long.class)))
                .willReturn(returningReport);

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report/1?start=1990-01-01&end=2050-01-01")

                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returningReport)));
    }

}
