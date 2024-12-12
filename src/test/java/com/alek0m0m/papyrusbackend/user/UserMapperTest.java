package com.alek0m0m.papyrusbackend.user;

import com.alek0m0m.papyrusbackend.config.InitData;
import com.alek0m0m.papyrusbackend.field.FieldDTOInput;
import com.alek0m0m.papyrusbackend.resource.ResourceDTOInput;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    @Mock
    private ResourceService resourceService;

    private final UserDTOInput[] usersInitData = InitData.getUsersInitData();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userMapper = new UserMapper(resourceService);
    }

    // ------------------ Tests ------------------
    // Tests overview:
        // operations:
            // convert          inputs: DTOInput        outputs: DTO
            // convert          inputs: entity,         outputs: DTO
            // convert          inputs: DTO,            outputs: entity
            // toDTO            inputs: DTOInput,       outputs: DTO
            // map              inputs: DTOInput,       outputs: DTO
            // map              inputs: DTO,            outputs: DTO (updated)


    @Test
    // test convert DTOInput to DTO
    void testConvert1() {
        // given
        List<UserDTOInput> users = List.of(usersInitData);

        printTest("Starting testConvert1: UserDTOInput -> UserDTO", " - UserDTOInput: " + users.get(0), " - UserDTO: " + new UserDTO());

        // when
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::convert)
                .toList();



        // then
        assertEquals(users.size(), userDTOs.size(), "The number of converted UserDTOs should match the number of UserDTOInputs");
        assertNotNull(userDTOs, "The list of UserDTOs should not be null");
        assertNotNull(userDTOs.get(0), "The first UserDTO in the list should not be null");

        printTest("testConvert1 passed: UserDTOInput converted to UserDTO", " - UserDTOInput: " + users.get(0), " - UserDTO: " + userDTOs.get(0));
    }

    @Test
    // test convert Entity to DTO
    void testConvert2() {
        // given
        User doe = new User()
                .setName("John Doe")
                .setEmail("doe@mail.com")
                .setPassword("password")
                .setRole("USER");
        User bob = new User()
                .setName("Bob")
                .setEmail("bob@mail.com")
                .setPassword("password123")
                .setRole("ADMIN");

        List<User> users = List.of(doe, bob);

        printTest("Starting testConvert2: User -> UserDTO", " - User: " + users.get(0), " - UserDTO: " + new UserDTO());

        // when
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::convert)
                .toList();

        // then
        assertEquals(users.size(), userDTOs.size(), "The number of converted UserDTOs should match the number of Users");
        assertNotNull(userDTOs, "The list of UserDTOs should not be null");
        assertNotNull(userDTOs.get(0), "The first UserDTO in the list should not be null");

        printTest("testConvert2 passed: User converted to UserDTO", " - User: " + users.get(0), " - UserDTO: " + userDTOs.get(0));
    }

    @Test
    // test convert DTO to Entity
    void testConvert3() {


    }


    @Test
    void toDTO() {
        // given
            // User
        UserDTOInput dto = new UserDTOInput()
                .setName("John Doe")
                .setEmail("john.doe@example.com")
                .setPassword("password")
                .setRole("USER");

            // field
        FieldDTOInput root = dto.getField();

            // resources
        ResourceDTOInput addedResource = new ResourceDTOInput()
                .setName("resource");
        ResourceDTOInput savedResource = new ResourceDTOInput()
                .setName("savedResource");

            // setting resources; root has resource, dto has savedResource
        root.setResources(List.of(addedResource));

        dto.setSavedResources(List.of(savedResource));
        dto.setField(root);

        printTest("Starting toDTO: UserDTOInput -> UserDTO", " - UserDTOInput: " + dto, " - UserDTO: " + new UserDTO());


        // when
        UserDTO userDTO = userMapper.toDTO(dto);

        // then
        assertNotNull(userDTO, "The converted UserDTO should not be null");
        assertEquals(dto.getName(), userDTO.getName(), "The name should match");
        assertEquals(dto.getEmail(), userDTO.getEmail(), "The email should match");
        assertEquals(dto.getPassword(), userDTO.getPassword(), "The password should match");
        assertEquals(dto.getRole(), userDTO.getRole(), "The role should match");
        assertNotNull(userDTO.getField(), "The field should not be null");
        assertNotNull(userDTO.getSavedResources(), "The saved resources should not be null");
        assertEquals(root.getName(), userDTO.getField().getName(), "The field name should match");
        assertEquals(addedResource.getName(), userDTO.getField().getResources().get(0).getName(), "The resource name should match");
        assertEquals(savedResource.getName(), userDTO.getSavedResources().get(0).getName(), "The saved resource name should match");

        printTest("testMap passed: UserDTO and User successfully mapped to updated UserDTO", " - UserDTO: " + userDTO, " - User: " + dto);
    }

    @Test
    void map() {
        // given
        UserDTOInput userDTOInput = new UserDTOInput();
        userDTOInput.setName("John Doe");
        userDTOInput.setEmail("john.doe@example.com");
        userDTOInput.setPassword("password");
        userDTOInput.setRole("USER");

        User user = new User();
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password123");
        user.setRole("ADMIN");

        printTest("Starting testMap: Mapping UserDTO and User to updated UserDTO", " - UserDTO: " + userDTOInput, " - User: " + user);

        // when
        UserDTO userDTO = userMapper.map(userDTOInput, user);

        // then
        assertNotNull(userDTO, "The mapped UserDTO should not be null");
        assertEquals(userDTOInput.getName(), userDTO.getName(), "The name should match");
        assertEquals(userDTOInput.getEmail(), userDTO.getEmail(), "The email should match");
        assertEquals(userDTOInput.getPassword(), userDTO.getPassword(), "The password should match");
        assertEquals(userDTOInput.getRole(), userDTO.getRole(), "The role should match");

        printTest("testMap passed: UserDTO and User successfully mapped to updated UserDTO", " - UserDTO: " + userDTO, " - User: " + user);
    }

    private static void printTest(String x, String userDTOInput, String user) {
        System.out.println(x);
        System.out.println(userDTOInput);
        System.out.println(user);
    }

    @Test
    void testMapUpdateEntity() {
        // given
        UserDTO newDTO = new UserDTO();
        newDTO.setName("John Doe");
        newDTO.setEmail("john.doe@example.com");
        newDTO.setPassword("password");
        newDTO.setRole("USER");

        User oldEntity = new User();
        oldEntity.setName("John Doe");
        oldEntity.setEmail("jane.doe@example.com");
        oldEntity.setPassword("password123");
        oldEntity.setRole("ADMIN");

        printTest("Starting testMap: Mapping UserDTO and User to updated UserDTO", " - UserDTO: " + newDTO, " - User: " + oldEntity);

        // when
        UserDTO updatedEntity = userMapper.map(newDTO, oldEntity);
            // updatedUserDTO should be: email {john.doe@example.com}, password {password}, role {USER}

        // then
        assertNotNull(updatedEntity, "The updated UserDTO should not be null");
        assertEquals(newDTO.getName(), updatedEntity.getName(), "The name should match");
        assertEquals(newDTO.getEmail(), updatedEntity.getEmail(), "The email should match");
        assertEquals(newDTO.getPassword(), updatedEntity.getPassword(), "The password should match");
        assertEquals(newDTO.getRole(), updatedEntity.getRole(), "The role should match");

        assertNotEquals(oldEntity.getEmail(), updatedEntity.getEmail(), "The email should be updated, and not match");

        printTest("testMap passed: UserDTO and User successfully mapped to updated UserDTO", " - UserDTO: " + newDTO, " - User: " + oldEntity);
    }

    @Test
    void testMapUpdateEntity_Not() {
        // given
        UserDTO newDTO = new UserDTO();
        newDTO.setName("John Doe");
        newDTO.setEmail("john.doe@example.com");
        newDTO.setPassword("password");
        newDTO.setRole("USER");

        User oldEntity = new User();
        oldEntity.setName("John Doe");
        oldEntity.setEmail("jane.doe@example.com");
        oldEntity.setPassword("password123");
        oldEntity.setRole("ADMIN");

        printTest("Starting testMap: Mapping UserDTO and User to updated UserDTO", " - UserDTO: " + newDTO, " - User: " + oldEntity);

        // when
        UserDTO updatedEntity = userMapper.map(newDTO, oldEntity);
            // updatedUserDTO should be: email {john.doe@example.com}, password {password}, role {USER}

        // then
        assertNotNull(updatedEntity, "The updated UserDTO should not be null");
        assertEquals(newDTO.getName(), updatedEntity.getName(), "The name should match");
        assertEquals(newDTO.getEmail(), updatedEntity.getEmail(), "The email should match");
        assertEquals(newDTO.getPassword(), updatedEntity.getPassword(), "The password should match");
        assertEquals(newDTO.getRole(), updatedEntity.getRole(), "The role should match");

        assertNotEquals(oldEntity.getEmail(), updatedEntity.getEmail(), "The email should be updated, and not match");

        printTest("testMap passed: UserDTO and User successfully mapped to updated UserDTO", " - UserDTO: " + newDTO, " - User: " + oldEntity);
    }
}