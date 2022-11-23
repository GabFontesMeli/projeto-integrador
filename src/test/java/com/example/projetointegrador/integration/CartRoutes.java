package com.example.projetointegrador.integration;

import java.io.File;

import java.io.IOException;

import com.example.projetointegrador.dto.CompletedFinanceReportCartDTO;
import com.example.projetointegrador.exceptions.*;

import java.util.Arrays;
import java.util.List;

import com.example.projetointegrador.dto.CartStatusDTO;
import com.example.projetointegrador.exceptions.*;
import com.example.projetointegrador.model.BatchProduct;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.projetointegrador.dto.CartDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class CartRoutes {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String path = "src/test/java/com/example/projetointegrador/utils";

    @Test
    @Order(1)
    void createCartShouldReturnDouble() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCart(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(16.0));
    }


    @Test
    @Order(2)
    void changeCartStatusShouldReturnString() throws Exception {
        CartStatusDTO cartStatusDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/changeCartStatus(CartStatusDTO).json"), CartStatusDTO.class);
        String payload = objectMapper.writeValueAsString(cartStatusDTO);

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/1")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Cart is CLOSED"));
    }

    @Test
    void createCartShouldReturnExceptionUserNotFoud_thenExpectationSatisfied() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCartUserInvalid(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserUNotFoundException));

    }
    @Test
    void createCartShouldReturnInsufficientStockException_thenExpectationSatisfied() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCartInsufficientStock(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isUnprocessableEntity())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InsufficientStockException));
    }
    @Test
    void createCartShouldReturnProductNotFoundException_thenExpectationSatisfied() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCartProductNotFound(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException));
    }
    @Test
    void createCartShouldReturnExpiredProductException_thenExpectationSatisfied() throws Exception {
        CartDTO cartDTO = objectMapper.readValue(new File(path + "/requestsBody/Cart/createCartExpiredProduct(CartDTO).json"), CartDTO.class);
        String payload = objectMapper.writeValueAsString(cartDTO);

        this.mockMvc
                .perform(
                        post("/api/v1/fresh-products/orders")
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isConflict())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExpiredProductException));
    }

    @Test
    void financeReportByPeriodShouldReturnCompletedFinanceReportCartDTO() throws Exception {

        CompletedFinanceReportCartDTO responseJson = objectMapper.readValue(
                new File(path + "/responsesBody/Cart/financeReportByPeriodResponse.json"), CompletedFinanceReportCartDTO.class);

        String startDate = "2023-01-21";
        String endDate = "2023-04-30";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/finance-report-by-period/{startDate}/{endDate}",
                                startDate, endDate)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseJson)));
    }

    @Test
    void financeReportByPeriodShouldThrowsInvalidDateFormatException() throws Exception {

        CompletedFinanceReportCartDTO responseJson = objectMapper.readValue(
                new File(path + "/responsesBody/Cart/financeReportByPeriodResponse.json"), CompletedFinanceReportCartDTO.class);

        String wrongStartDateFormat = "2020-------01-01";
        String wrongEndDateFormat = "2023-------04-30";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/finance-report-by-period/{startDate}/{endDate}",
                                wrongStartDateFormat, wrongEndDateFormat)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidDateFormatException))
                .andExpect(result -> assertEquals("Send a date with the correct format: yyyy-MM-dd", result.getResolvedException().getMessage()));
    }

    @Test
    void financeReportByPeriodShouldThrowsCartNotFoundException() throws Exception {

        CompletedFinanceReportCartDTO responseJson = objectMapper.readValue(
                new File(path + "/responsesBody/Cart/financeReportByPeriodResponse.json"), CompletedFinanceReportCartDTO.class);

        String inexistentStartDate = "2090-01-01";
        String inexistentEndDate = "2090-04-30";

        this.mockMvc
                .perform(
                        get("/api/v1/fresh-products/orders/finance-report-by-period/{startDate}/{endDate}",
                                inexistentStartDate, inexistentEndDate)
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CartNotFoundException))
                .andExpect(result -> assertEquals("Could not found carts with the given dates", result.getResolvedException().getMessage()));
    }
    @Test
    void cancelOrderShouldReturnCartStatusDTO() throws Exception {

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/{cartId}/{userId}", 2, 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELED"));

    }

    @Test
    void cancelOrderShouldReturnInvalidUserException_thenExpectationSatisfied() throws Exception {

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/{cartId}/{userId}", 2, 2)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidUserException));

    }

    @Test
    void cancelOrderShouldReturnExpiredCancellationPeriodException_thenExpectationSatisfied() throws Exception {

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/{cartId}/{userId}", 1, 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ExpiredCancellationPeriodException));

    }

    @Test
    void cancelOrderShouldReturnUnfinishedOrderException_thenExpectationSatisfied() throws Exception {

        this.mockMvc
                .perform(
                        put("/api/v1/fresh-products/orders/{cartId}/{userId}", 5, 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UnfinishedOrderException));

    }
}
