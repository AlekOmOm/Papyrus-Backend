package com.alek0m0m.papyrusbackend.config;

import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InitDataStepsBDD {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private InitData initData;

    //Given the application is running
    public void theApplicationIsRunning() {
    }

    //When the init data
    public void theInitdataIsLoaded() throws Exception {
        initData.run();
    }

    //Then the users and ressources from initData shall be saved

    public void theRessourcesFromInitDataIsSaved(int RessourceCount) {
        assertEquals(RessourceCount, resourceRepository.findAll().size());

    }

    public void theUsersFromInitDataIsSaved(int UserCount) {
        assertEquals(UserCount, resourceRepository.findAll().size());
    }


}


