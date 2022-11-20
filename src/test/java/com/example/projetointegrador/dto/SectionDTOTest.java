package com.example.projetointegrador.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class SectionDTOTest {

    @Test
    public void test() {
        Long id = 1L;
        Long storageId = 1L;

        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(id);
        sectionDTO.setStorageId(storageId);

        assertThat(sectionDTO).isNotNull();
        assertThat(sectionDTO.getId()).isEqualTo(id);
        assertThat(sectionDTO.getStorageId()).isEqualTo(storageId);
    }
}
