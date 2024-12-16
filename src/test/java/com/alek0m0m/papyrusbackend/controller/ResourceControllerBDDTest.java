package com.alek0m0m.papyrusbackend.controller;

import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerBDDTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private ResourceRepository resourceRepository;

    @BeforeEach
    public void setUp() {
        resourceRepository.deleteAll();
    }


    @Test
    public void CreateResource_ShouldSaveToDatabase_WhenValidDataProvided() throws Exception {

        //Given: a resource object with a unique refId
        Resource resource = new Resource();
        resource.setName("Learning Java");
        resource.setRefId("123456");

        //When: the resource is sent to the endpoint
        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))

                //Then: the resource is saved successfully, and the response status is 200 OK
                .andExpect(status().isOk())

                //And: the response contains the resource name and refId
                .andExpect(jsonPath("$.name").value("Learning Java"))
                .andExpect(jsonPath("$.refId").value("123456"));

        System.out.println("Resource saved successfully" + resource);
    }
}
