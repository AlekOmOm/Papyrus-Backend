package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.config.InitData;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import com.alek0m0m.papyrusbackend.resource.ResourceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserMapperTest {


    @InjectMocks
    private UserMapper userMapper = new UserMapper();



    private final UserDTOInput[] usersInitData = InitData.getUsersInitData();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    // test convert DTOInput to DTO
    void testConvert() {
        // given
        List<UserDTOInput> users = List.of(usersInitData);

        // when
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::convert)
                .toList();

        // then
        assertEquals(users.size(), userDTOs.size());
        assertNotNull(userDTOs);
        assertNotNull(userDTOs.get(0));
    }

    @Test
    void testConvert1() {
    }

    @Test
    void testConvert2() {
    }
}