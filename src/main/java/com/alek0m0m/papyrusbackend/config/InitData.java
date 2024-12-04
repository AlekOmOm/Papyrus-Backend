package com.alek0m0m.papyrusbackend.config;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldRepository;
import com.alek0m0m.papyrusbackend.field.FieldService;
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
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldRepository fieldRepository;

    public final UserDTOInput[] users = {
            new UserDTOInput(0, "name", "email", "password", "role"),
            new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
            new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
            new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };

    @Override
    public void run(String... args) throws Exception {
        System.out.println("InitData.run...");

        initUsers();

        initResources();

        initFields();
    }

    // Makes fields one to one with user
    private void initFields(){
        Field testField1 = new Field().setName("TestField1").setUser(userRepository.findById(1L).orElse(null));
        Field testField2 = new Field().setName("TestField2").setUser(userRepository.findById(2L).orElse(null));
        Field testField3 = new Field().setName("TestField3").setUser(userRepository.findById(3L).orElse(null));

        printCount(" before", "field", fieldService);
        saveFields(testField1, testField2, testField3);
        printCount(" after", "field", fieldService);
    }

    private void saveFields(Field... fields){
        for (Field field : fields) {
            fieldRepository.save(field);
        }}

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
        resourceRepository.save(Testressource1);
        resourceRepository.save(Testressource2);
        resourceRepository.save(Testressource3);
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