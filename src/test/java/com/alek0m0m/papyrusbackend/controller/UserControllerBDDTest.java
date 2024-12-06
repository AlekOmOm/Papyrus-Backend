package com.alek0m0m.papyrusbackend.controller;

import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import com.alek0m0m.papyrusbackend.user.UserController;
import com.alek0m0m.papyrusbackend.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerBDDTest {

    @MockBean
    private ResourceService resourceService;

    @MockBean
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setup() {
        userController = new UserController(userService, resourceService);
    }

    @Test
    void givenValidUserAndResource_whenSaveAsPersonalResource_thenResourceIsSaved() {
        // given
        Long resourceId = 1L;
        Long userId = 1L;
        ResourceDTO mockResource = new ResourceDTO();
        mockResource.setId(resourceId);
        mockResource.setName("Resource Name");

        when(resourceService.savePersonalResource(resourceId, userId)).thenReturn(mockResource);

        // when
        ResponseEntity<ResourceDTO> response = userController.saveAsPersonalResource(resourceId, userId);

        // then
        assertEquals(200, response.getStatusCodeValue(), "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(resourceId, response.getBody().getId(), "Resource ID should match");
        assertEquals("Resource Name", response.getBody().getName(), "Resource name should match");

        // Print statements to verify the output
        System.out.println("Status code: " + response.getStatusCodeValue());
        System.out.println("Response body: " + response.getBody());
        System.out.println("Resource ID: " + response.getBody().getId());
        System.out.println("Resource Name: " + response.getBody().getName());

        // Verify the connection between resource and user
        verify(resourceService, times(1)).savePersonalResource(resourceId, userId);
        System.out.println("Resource saved for user with ID: " + userId);
    }
}