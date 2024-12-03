package com.alek0m0m.papyrusbackend.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        // given
        UserDTOInput userDTOInput = new UserDTOInput(0, "name", "email", "password", "role");
        UserDTO userDTO = new UserDTO().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("role");
        when(userService.save(any(UserDTO.class))).thenReturn(userDTO);

        // when
        ResponseEntity<UserDTO> response = userController.create(userDTOInput);

        // then
        assertEquals(ResponseEntity.ok(userDTO), response);
        verify(userService)
                .save(userService.getDtoMapper()
                        .convert(userDTOInput));
    }

    @Test
    void testGetAll() {
        // given
        UserDTO userDTO = new UserDTO().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("role");
        List<UserDTO> userList = Collections.singletonList(userDTO);

        // when
        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<List<UserDTO>> response = userController.getAll();

        // then
        assertEquals(ResponseEntity.ok(userList), response);
        verify(userService).findAll();
    }

    @Test
    void testGetById() {
        UserDTO userDTO = new UserDTO().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("role");

        when(userService.findById(1L)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getById(1L);

        assertEquals(ResponseEntity.ok(userDTO), response);
        verify(userService).findById(1L);
    }

    @Test
    void testUpdate() {
        UserDTOInput userDTOInput = new UserDTOInput(1L, "name", "email", "password", "role");
        UserDTO userDTO = new UserDTO().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("role");

        when(userService.findById(1L)).thenReturn(userDTO);
        when(userService.save(any(UserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.update(1L, userDTOInput);

        assertEquals(ResponseEntity.ok(userDTO), response);
        verify(userService).findById(1L);
        verify(userService).save(any(UserDTO.class));
    }

    @Test
    void testDelete() {
        UserDTO userDTO = new UserDTO().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("role");

        when(userService.findById(1L)).thenReturn(userDTO);
        doNothing().when(userService).deleteById(1L);

        ResponseEntity<Void> response = userController.delete(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(userService).findById(1L);
        verify(userService).deleteById(1L);
    }

    @Test
    void testDeleteAll() {
        doNothing().when(userService).deleteAll();

        ResponseEntity<Void> response = userController.deleteAll();

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(userService).deleteAll();
    }
}