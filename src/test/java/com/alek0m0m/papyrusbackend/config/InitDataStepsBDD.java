package com.alek0m0m.papyrusbackend.config;

import com.alek0m0m.papyrusbackend.ressource.ResourceRepository;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InitDataStepsBDD {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private InitData initData;

}
