package com.alek0m0m.papyrusbackend.config;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.ressource.ResourceService;
import com.alek0m0m.papyrusbackend.ressource.Resource;
import com.alek0m0m.papyrusbackend.ressource.ResourceRepository;
import com.alek0m0m.papyrusbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;


@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private ResourceRepository ressourceRepository;

    @Autowired
    private UserRepository userRepository;
    private final UserDTOInput[] users = {
            new UserDTOInput(0, "name", "email", "password", "role"),
            new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
            new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
            new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void run(String... args) throws Exception {


        System.out.println("InitData.run...");

        initUsers();

        initResources();


    }

    private void initResources() {
        // Test ressources
        Resource Testressource1 = new Resource("Ressource1", "Author1", LocalDate.now(), LocalDate.now().plusDays(1));
        Resource Testressource2 = new Resource("Ressource2", "Author2", LocalDate.now(), LocalDate.now().plusDays(2));
        Resource Testressource3 = new Resource("Ressource3", "Author3", LocalDate.now(), LocalDate.now().plusDays(3));

        // Saving the Test users and ressources
        printCount(" before", "ressource", resourceService);
        saveResources(Testressource1, Testressource2, Testressource3);
        printCount(" after", "ressource", resourceService);
    }

    private void saveResources(Resource Testressource1, Resource Testressource2, Resource Testressource3) {
        ressourceRepository.save(Testressource1);
        ressourceRepository.save(Testressource2);
        ressourceRepository.save(Testressource3);
    }


    private void printCount(String message, String type, BaseService service) {
        int count = service.findAll().size();

        System.out.println(" - "+message+", total "+type+" saved: " + count);
    }


    void initUsers() {
        printCount(" before", "user", userService);
        Arrays.stream(users)
                .map(userMapper::convert)
                .forEach(userService::save);
        printCount(" after", "user", userService);
    }
}