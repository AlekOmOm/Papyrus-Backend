package com.alek0m0m.papyrusbackend.Config;

import com.alek0m0m.papyrusbackend.Ressource.RessourceRepository;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InitDataStepsBDD {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RessourceRepository ressourceRepository;
    @Autowired
    private InitData initData;

}