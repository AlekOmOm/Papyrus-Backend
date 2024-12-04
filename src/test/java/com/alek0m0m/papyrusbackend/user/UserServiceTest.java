package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.FieldDTOInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseRESTController {

    private final UserDTOInput[] users = {
            new UserDTOInput(0, "name", "email", "password", "role",
            new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
            new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
            new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };

    private final FieldDTOInput[] fields = {
            new FieldDTOInput(0, "field1"),
            new FieldDTOInput(0, "field2"),
            new FieldDTOInput(0, "field3")
    };

    protected UserServiceTest(BaseService service) {
        super(service);
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void save() {
        // given, when, then
        // 1 given







    }

    @Test
    void saveAll() {

    }

    @Test
    void testUpdate() {
    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void testDeleteAll() {
    }

    @Test
    void findByNameAndEmail() {
    }
}