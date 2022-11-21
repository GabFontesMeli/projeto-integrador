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
public class SectionTest {

    @Test
    public void test(){
        Long id = 100L;
        String name = "name";
        Float temperature = 4.6f;
        Set<BatchProduct> batchProducts = new HashSet<>();
        Storage storage = new Storage();
        Category category = new Category();
        Float volume = 4.5f;


        Section section = new Section();
        section.setId(id);
        section.setName(name);
        section.setTemperature(temperature);
        section.setVolume(volume);
        section.setCategory(category);
        section.setBatchProducts(batchProducts);
        section.setStorage(storage);


        assertThat(id).isEqualTo(section.getId());
        assertThat(volume).isEqualTo(section.getVolume());
        assertThat(name).isEqualTo(section.getName());
        assertThat(temperature).isEqualTo(section.getTemperature());
        assertThat(batchProducts).isEqualTo(section.getBatchProducts());
        assertThat(storage).isEqualTo(section.getStorage());
        assertThat(category).isEqualTo(section.getCategory());


    }
}
