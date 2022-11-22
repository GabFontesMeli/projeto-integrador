package com.example.projetointegrador.integration.report;

import com.example.projetointegrador.exceptions.PeriodInvalidException;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class SalesProductReport extends BaseTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getSalesProductReportShouldReturnReport() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report?start=2020-11-11&end=2020-11-13")

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesProductReport").value("Sales products report between 2020-11-11 and 2020-11-13"))
                .andExpect(jsonPath("$.totalProduct").value(2))
                .andExpect(jsonPath("$.products",hasSize(1)))
                .andExpect(jsonPath("$.products[0].productName").value("banana"))
                .andExpect(jsonPath("$.products[0].quantity").value(2));
    }

    @Test
    void getSalesProductReportShouldReturnPeriodInvalidException() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report?start=2020-11-11&end=2020-11-10")

                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PeriodInvalidException));
    }

    @Test
    void getSalesProductReportShouldReturnEmptyReport() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report?start=2020-10-11&end=2020-10-13")

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesProductReport").value("Sales products report between 2020-10-11 and 2020-10-13 doesn't have any data"))
                .andExpect(jsonPath("$.totalProduct").value(0));
    }

    @Test
    void getSalesProductReportByUserShouldReturnReport() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report/1?start=2020-11-11&end=2020-11-13")

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesProductReport").value("Sales products report between 2020-11-11 and 2020-11-13"))
                .andExpect(jsonPath("$.user").value("teste"))
                .andExpect(jsonPath("$.totalProduct").value(2))
                .andExpect(jsonPath("$.products",hasSize(1)))
                .andExpect(jsonPath("$.products[0].productName").value("banana"))
                .andExpect(jsonPath("$.products[0].quantity").value(2));
    }

    @Test
    void getSalesProductReportByUserShouldReturnPeriodInvalidException() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report/1?start=2020-11-11&end=2020-11-10")

                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof PeriodInvalidException));
    }

    @Test
    void getSalesProductReportByUserShouldReturnEmptyReport() throws Exception {

        this.mockMvc
                .perform(
                        get("/api/v1/sales-report/1?start=2020-10-11&end=2020-10-13")

                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesProductReport").value("Sales products report between 2020-10-11 and 2020-10-13 doesn't have any data"))
                .andExpect(jsonPath("$.totalProduct").value(0));
    }
}
