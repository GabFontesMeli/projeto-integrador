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
public class CartItemTest {

    @Test
    public void test(){
        Long id = 1L;
        Cart cart = new Cart();
        BatchProduct batchProduct = new BatchProduct();
        Integer quantity = 10;
        Double itemValue = 1.5;

        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setCart(cart);
        cartItem.setQuantity(quantity);
        cartItem.setItemValue(itemValue);
        cartItem.setBatchProduct(batchProduct);

        assertThat(cartItem).isNotNull();
        assertThat(id).isEqualTo(cartItem.getId());
        assertThat(cart).isEqualTo(cartItem.getCart());
        assertThat(quantity).isEqualTo(cartItem.getQuantity());
        assertThat(itemValue).isEqualTo(cartItem.getItemValue());
        assertThat(batchProduct).isEqualTo(cartItem.getBatchProduct());

    }
}
