package com.alek0m0m.papyrusbackend.controller;

import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerTest {

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

    @AfterEach
    public void tearDown() {
        resourceRepository.deleteAll();
    }

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

    @Test
    public void CreateResource_ShouldSaveToDatabase_WhenValidDataProvided() throws Exception {

        Resource resource = new Resource();
        resource.setName("Learning Java");
        resource.setRefId("123456");

        mockMvc.perform(post("/api/resources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Learning Java"))
                .andExpect(jsonPath("$.refId").value("123456"));

        System.out.println("Resource saved successfully" + resource);
    }

    @Test
    public void testDuplicateRefIdCannotBeSaved() {
        System.out.println("Initial database count: " + resourceRepository.count());
        resourceRepository.findAll().forEach(resource -> System.out.println("Resource: " + resource));

        Resource resource1 = new Resource();
        resource1.setName("Test Name 1");
        resource1.setRefId("UniqueRefId");
        resourceRepository.save(resource1);
        System.out.println("Saved resource1: " + resource1);

        Resource resource2 = new Resource();
        resource2.setName("Test Name 2");
        resource2.setRefId("UniqueRefId");

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            resourceRepository.save(resource2);
        });

        System.out.println("Final database count: " + resourceRepository.count());
        resourceRepository.findAll().forEach(resource -> System.out.println("Resource: " + resource));
    }

    @Test
    public void testMissingRefIdCannotBeSaved() {
        Resource resource = new Resource();
        resource.setName("Test Name");
        resource.setRefId(null);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            resourceRepository.save(resource);
        });

        List<Resource> allResources = resourceRepository.findAll();
        Assertions.assertFalse(allResources.stream().anyMatch(r -> r.getName().equals("Test Name")));
    }

}
