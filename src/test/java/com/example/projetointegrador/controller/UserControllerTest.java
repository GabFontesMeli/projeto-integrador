package com.example.projetointegrador.controller;

import com.example.projetointegrador.dto.BatchDTO;
import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.exceptions.UserUNotFoundException;
import com.example.projetointegrador.model.Batch;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.service.UserService;
import com.example.projetointegrador.setup.BaseTest;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void saveUser() {
    }

    @Test
    void getUsersShouldReturnListOfUsersDTO() throws Exception {

        List<UserDTO> returningUsers = Arrays.asList(objectMapper.readValue(new File(path + "/responsesBody/User/getUsersResponse.json"), UserDTO[].class));

        BDDMockito.given(userService.getUsers())
                .willReturn(returningUsers);

        this.mockMvc
                .perform(
                        get("/api/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returningUsers)));
    }

    @Test
    void getUserByIdShouldReturnUserDTO() throws Exception {

        UserDTO returningUser = objectMapper.readValue(new File(path + "/responsesBody/User/getUserByIdResponse.json"), UserDTO.class);

        BDDMockito.given(userService.getUserById(any(Long.class)))
                .willReturn(returningUser);

        this.mockMvc
                .perform(
                        get("/api/v1/user/{userId}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(returningUser)));
    }

    @Test
    void updateUserShouldReturnUserDTO() throws Exception {
        UserDTO returningUser = objectMapper.readValue(new File(path + "/responsesBody/User/getUserByIdResponse.json"), UserDTO.class);

        BDDMockito.given(userService.updateUser(any(Long.class), any(UserDTO.class)))
                .willReturn(returningUser);

        UserDTO payload = objectMapper.readValue(new File(path + "/responsesBody/User/updateUserResponse.json"), UserDTO.class);

        this.mockMvc
                .perform(
                        put("/api/v1/user/1")
                                .content(objectMapper.writeValueAsString(payload))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(content().json(objectMapper.writeValueAsString(returningUser)));
    }

    @Test
    void deleteUserShouldReturnNoContent() throws Exception {

        BDDMockito.doNothing().when(userService).deleteUser(any(Long.class));

        this.mockMvc
                .perform(
                        delete("/api/v1/user/{userId}", 1)
                )
                .andExpect(status().isNoContent());
    }
}
