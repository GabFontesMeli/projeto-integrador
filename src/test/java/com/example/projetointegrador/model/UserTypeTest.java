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
public class UserTypeTest {

    @Test
    public void test(){
        Long id = 1L;
        String type = "type";
        Set<UserU> users = new HashSet<>();

        UserType userType = new UserType();
        userType.setId(id);
        userType.setType(type);
        userType.setUsers(users);

        assertThat(id).isEqualTo(userType.getId());
        assertThat(type).isEqualTo(userType.getType());
        assertThat(users).isEqualTo(userType.getUsers());
    }
}
