package com.alek0m0m.papyrusbackend.user;

import com.alek0m0m.papyrusbackend.PapyrusBackendApplication;
import com.alek0m0m.papyrusbackend.config.InitData;
import com.alek0m0m.papyrusbackend.field.FieldMapper;
import com.alek0m0m.papyrusbackend.resource.ResourceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private FieldMapper fieldMapper;

    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;


    private final UserDTOInput[] usersInitData = InitData.getUsersInitData();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userMapper = new UserMapper(fieldMapper);
        userService = new UserService(userRepository, userMapper);
    }

    @Test
    void save() {
        // given
        List<UserDTOInput> users = List.of(usersInitData);
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::convert)
                .toList();


        // when
//        when(userMapper.convert(any(UserDTOInput.class))).thenAnswer(invocation -> {
//            UserDTOInput input = invocation.getArgument(0);
//            return new UserDTO()
//                    .setId(input.getId())
//                    .setName(input.getName())
//                    .setEmail(input.getEmail())
//                    .setPassword(input.getPassword())
//                    .setRole(input.getRole());
//        });
//
//        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
//            User user = invocation.getArgument(0);
//            return user;
//        });

        // when
        users.forEach(System.out::println);
        userDTOs.forEach(System.out::println);

        userDTOs.forEach(userService::save);

        // then
        verify(userRepository, times(users.size())).save(any(User.class));
    }

    @Test
    void findAll() {
        // given
        List<User> users = List.of(
                new User().setId(1L).setName("name").setEmail("email").setPassword("password").setRole("user"),
                new User().setId(2L).setName("bob").setEmail("Bob@mail.com").setPassword("123").setRole("user")
        );

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.convert(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            return new UserDTO()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .setRole(user.getRole());
        });

        // when
        List<UserDTO> allUsers = userService.findAll();

        // then
        assertEquals(users.size(), allUsers.size());
        assertTrue(allUsers.stream().anyMatch(user -> user.getName().equals("name")));
        assertTrue(allUsers.stream().anyMatch(user -> user.getName().equals("bob")));
    }
}