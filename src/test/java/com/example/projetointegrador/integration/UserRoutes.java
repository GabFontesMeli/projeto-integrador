package com.example.projetointegrador.integration;

import com.example.projetointegrador.dto.UserDTO;
import com.example.projetointegrador.model.BatchProduct;
import com.example.projetointegrador.model.Product;
import com.example.projetointegrador.setup.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRoutes {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    String path = "src/test/java/com/example/projetointegrador/utils";

    @Test
    @Order(1)
    void getAllUsersShouldReturnListOfUsers() throws Exception {

        List<UserDTO> responseJson = Arrays.asList((objectMapper.readValue(new File(path + "/responsesBody/User/getUsersResponse.json"), UserDTO[].class)));
        String response = objectMapper.writeValueAsString(responseJson);

        this.mockMvc
                .perform(
                        get("/api/v1/user")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @Order(2)
    void getUserByIdShouldReturnUserDTO() throws Exception {

        UserDTO responseJson = objectMapper.readValue(new File(path + "/responsesBody/User/getUserByIdResponse.json"), UserDTO.class);
        String response = objectMapper.writeValueAsString(responseJson);

        this.mockMvc
                .perform(
                        get("/api/v1/user/{userId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @Order(3)
    void updateUserShouldReturnUserDTO() throws Exception {
        UserDTO request = objectMapper.readValue(new File(path + "/responsesBody/User/updateUserResponse.json"), UserDTO.class);
        String payload = objectMapper.writeValueAsString(request);

        this.mockMvc
                .perform(
                        put("/api/v1/user/{userId}", 2)
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.email").value("email"));
    }

    @Test
    @Order(4)
    void deleteUserShouldReturnNoContent() throws Exception {

        this.mockMvc
                .perform(
                        delete("/api/v1/user/{userId}", 2)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }
}
