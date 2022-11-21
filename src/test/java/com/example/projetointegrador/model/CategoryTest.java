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
public class CategoryTest {

    @Test
    public void test(){
        Long id = 1L;
        String name = "name";
        Set<Product> products = new HashSet<>();
        Set<Section> sections = new HashSet<>();

        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setProducts(products);
        category.setSections(sections);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(id);
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getProducts()).isEqualTo(products);
        assertThat(category.getSections()).isEqualTo(sections);
    }
}
