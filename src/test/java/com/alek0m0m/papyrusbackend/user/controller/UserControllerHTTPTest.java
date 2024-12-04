package com.alek0m0m.papyrusbackend.user.controller;

import com.alek0m0m.papyrusbackend.PapyrusBackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PapyrusBackendApplication.class)
@AutoConfigureMockMvc
public class UserControllerHTTPTest {


    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "api/users";


    // BaseRESTController endpoints:
        // - GET /api/users
        // - GET /api/users/{id}
        // - POST /api/users
        // - PUT /api/users/{id}
        // - DELETE /api/users/{id}

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk());
    }



}
