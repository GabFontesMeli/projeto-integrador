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
public class CartStatusDTOTest {

    @Test
    public void test() {
        String status = "status";

        CartStatusDTO cartStatusDTO = new CartStatusDTO();
        cartStatusDTO.setStatus(status);

        assertThat(cartStatusDTO).isNotNull();
        assertThat(cartStatusDTO.getStatus()).isEqualTo(status);
    }
}
