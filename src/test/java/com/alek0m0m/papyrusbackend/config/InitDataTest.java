package com.alek0m0m.papyrusbackend.config;

import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;



// this test class is used to test if the data can be saved to mocked repositories
class InitDataTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private InitData initData;

    @BeforeEach
    void setUp() {
        //initialize the mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void run() throws Exception {
        //call the run method
        initData.run();

        //secures that the save method is called 3 times for each repository
        //The save method is called 3 times for each repository because there are 3 users and 3 ressources in initdata
        verify(userRepository, times(3)).save(ArgumentMatchers.any(User.class));
        verify(resourceRepository, times(3)).save(ArgumentMatchers.any(Resource.class));
    }
}