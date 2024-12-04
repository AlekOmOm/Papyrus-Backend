package com.alek0m0m.papyrusbackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFailToSaveResourceWithoutImplementation() throws Exception {
        String resourceJson = """
            {
                "userId": 1,
                "resourceName": "Learning Java",
                "resourceUrl": "https://example.com/java-tutorial"
            }
        """;

        mockMvc.perform(post("/api/resources/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(resourceJson))
                .andExpect(status().isCreated());
    }
}
