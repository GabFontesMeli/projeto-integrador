package com.example.projetointegrador.model;

import com.example.projetointegrador.enums.CartStatusEnum;
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
public class CartTest {

    @Test
    public void test(){
        Long id = 1L;
        LocalDate date = LocalDate.now();
        UserU user = new UserU();
        Double totalValue = 5.0;
        CartStatusEnum status = CartStatusEnum.OPEN;
        Set<CartItem> cartItems = new HashSet<>();

        Cart cart = new Cart();
        cart.setId(id);
        cart.setDate(date);
        cart.setUser(user);
        cart.setTotalValue(totalValue);
        cart.setStatus(status);
        cart.setCartItems(cartItems);

        assertThat(cart).isNotNull();
        assertThat(id).isEqualTo(cart.getId());
        assertThat(date).isEqualTo(cart.getDate());
        assertThat(user).isEqualTo(cart.getUser());
        assertThat(totalValue).isEqualTo(cart.getTotalValue());
        assertThat(status).isEqualTo(cart.getStatus());
        assertThat(cartItems).isEqualTo(cart.getCartItems());

    }
}
