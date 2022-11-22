package com.example.projetointegrador.service;

import com.example.projetointegrador.dto.CompletedFinanceReportCartDTO;
import com.example.projetointegrador.model.Cart;
import com.example.projetointegrador.repository.CartRepository;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest extends BaseTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Test
    void financeReportByPeriodShouldReturnCompletedSaleReportCartDTO() throws IOException {

        ObjectMapper mp = new ObjectMapper();
        mp.findAndRegisterModules();

        CompletedFinanceReportCartDTO responseJson = mp.readValue(
                new File(path + "/responsesBody/Cart/financeReportByPeriodResponse.json"), CompletedFinanceReportCartDTO.class);

        List<Cart> cartListMock = Arrays.asList(mp.readValue(
                new File(path + "/responsesBody/Cart/cartListResponse.json"), Cart[].class));

        BDDMockito.given(cartRepository.findCartsByDateGreaterThanEqualAndDateLessThanEqual(any(LocalDate.class), any(LocalDate.class)))
                .willReturn(cartListMock);

        CompletedFinanceReportCartDTO completedSaleReportCartDTO;
        try {
            completedSaleReportCartDTO = cartService.financeReportByPeriod("2023-01-21", "2023-04-30");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertThat(mp.writeValueAsString(completedSaleReportCartDTO))
                .isEqualTo(mp.writeValueAsString(responseJson));
    }

}
