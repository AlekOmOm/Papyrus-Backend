package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.BaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.alek0m0m.papyrusbackend.config.InitData.getUsersInitData;
import static java.util.stream.Collectors.toList;

class UserServiceTest extends BaseRESTController {

    private final UserDTOInput[] usersInitData = getUsersInitData();


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
        List<UserDTOInput> users = Arrays.stream(usersInitData).toList();
        List<BaseEntityDTO> userDTOs = users.stream()
                .map(mapper)
                        .toList();


        // 2 when
        userDTOs.stream()
                .map(service::save)
                .forEach(System.out::println);

        // 3 then



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