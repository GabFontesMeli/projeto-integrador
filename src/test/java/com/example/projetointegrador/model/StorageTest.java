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
public class StorageTest {

    @Test
    public void test(){
        Long id = 100L;
        Float volume = 4.6f;
        Set<Batch> batches = new HashSet<Batch>();
        Set<Section> sections = new HashSet<>();
        Set<UserU> users = new HashSet<>();

        Storage storage = new Storage();
        storage.setId(id);
        storage.setVolume(volume);
        storage.setBatches(batches);
        storage.setSections(sections);
        storage.setUsers(users);

        assertThat(id).isEqualTo(storage.getId());
        assertThat(volume).isEqualTo(storage.getVolume());
        assertThat(batches).isEqualTo(storage.getBatches());
        assertThat(sections).isEqualTo(storage.getSections());
        assertThat(users).isEqualTo(storage.getUsers());

    }
}
