package com.alek0m0m.papyrusbackend.Config;

import com.alek0m0m.papyrusbackend.Ressource.Ressource;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class InitDataTest {

    @Mock
    private RessourceRepository ressourceRepository;

    private UserRepository userRepository;

    @InjectMocks
    private InitData initData;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);
        ressourceRepository = Mockito.mock(RessourceRepository.class);
        initData.ressourceRepository = ressourceRepository;
    }


    @Test
    void run() throws Exception{
            initData.run();

            Mockito.verify(userRepository, times(3)).save(ArgumentMatchers.any(User.class));
            Mockito.verify(ressourceRepository, times(3)).save(ArgumentMatchers.any(Ressource.class));
    }

}