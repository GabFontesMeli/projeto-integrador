package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.CompletedFinanceReportCartDTO;
import com.example.projetointegrador.service.CartService;
import com.example.projetointegrador.setup.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    void saleReportByPeriodShouldReturnCompletedSaleReportCartDTO() throws Exception {

        CompletedFinanceReportCartDTO responseJson = objectMapper.readValue(
                new File(path + "/responsesBody/Cart/financeReportByPeriodResponse.json"), CompletedFinanceReportCartDTO.class);

        BDDMockito.given(cartService.financeReportByPeriod(any(String.class), any(String.class)))
                .willReturn(responseJson);

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/finance-report-by-period/{{startDate}}/{{endDate}}",
                                "2021-01-01", "2021-01-31")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseJson)));
    }


}
