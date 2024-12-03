package com.alek0m0m.papyrusbackend.Config;

import com.alek0m0m.papyrusbackend.Ressource.Ressource;
import com.alek0m0m.papyrusbackend.Ressource.RessourceRepository;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

// the Ressouce repo is created to do the save method

class InitDataTest {


    @Mock
    private RessourceRepository ressourceRepository;

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
        initData.run();

        //secures that the save method is called 3 times for each repository
        //The save method is called 3 times for each repository because there are 3 users and 3 ressources in initdata
        verify(userRepository, times(3)).save(ArgumentMatchers.any(User.class));
        verify(ressourceRepository, times(3)).save(ArgumentMatchers.any(Ressource.class));
    }
}