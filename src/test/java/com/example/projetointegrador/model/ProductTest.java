package com.example.projetointegrador.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    @Test
    public void test(){
        Long id = 1L;
        String name = "name";
        Double price = 5.5;
        Float volume = 1.0f;
        Set<UserU> users = new HashSet<>();
        Set<BatchProduct> batchProduct = new HashSet<>();
        Category category = new Category();

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setVolume(volume);
        product.setUsers(users);
        product.setBatchProduct(batchProduct);
        product.setCategory(category);

        assertThat(product).isNotNull();
        assertThat(id).isEqualTo(product.getId());
        assertThat(name).isEqualTo(product.getName());
        assertThat(price).isEqualTo(product.getPrice());
        assertThat(volume).isEqualTo(product.getVolume());
        assertThat(users).isEqualTo(product.getUsers());
        assertThat(batchProduct).isEqualTo(product.getBatchProduct());
        assertThat(category).isEqualTo(product.getCategory());

    }
}
