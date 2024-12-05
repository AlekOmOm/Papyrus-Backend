package com.alek0m0m.papyrusbackend.config;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTOInput;
import com.alek0m0m.papyrusbackend.field.FieldRepository;
import com.alek0m0m.papyrusbackend.field.FieldService;
import com.alek0m0m.papyrusbackend.resource.*;
import com.alek0m0m.papyrusbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired
    private ResourceMapper resourceMapper;

    public static UserDTOInput[] getUsersInitData() {
        UserDTOInput user1 = new UserDTOInput()
                .setName("name")
                .setEmail("email")
                .setPassword("password")
                .setRole("user");

        UserDTOInput user2 = new UserDTOInput()
                .setName("bob")
                .setEmail("Bob@mail.com")
                .setPassword("123")
                .setRole("user");

        UserDTOInput user3 = new UserDTOInput()
                .setName("Alice")
                .setEmail("Alice@mail.com")
                .setPassword("123")
                .setRole("user");


        UserDTOInput user4 = new UserDTOInput()
                .setName("Admin")
                .setEmail("Admin@mail.com")
                .setPassword("123")
                .setRole("admin");

//        user1.setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[0]))));
//        user2.setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[1]))));
//        user3.setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[2]))));
//        user4.setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[3]))));

        return new UserDTOInput[]{user1, user2, user3, user4};
    }

    public static ResourceDTOInput[] getResourcesInitData() {
        return new ResourceDTOInput[]{
                new ResourceDTOInput()
                        .setName("Metaphysics")
                        .setAuthor("Aristotle")
                        .setFromDate(LocalDate.of(350, 1, 1))
                        .setToDate(LocalDate.of(350, 12, 31)),
                new ResourceDTOInput()

                        .setName("The Republic")
                        .setAuthor("Plato")
                        .setFromDate(LocalDate.of(380, 1, 1))
                        .setToDate(LocalDate.of(380, 12, 31)),
                new ResourceDTOInput()

                        .setName("The Iliad")
                        .setAuthor("Homer")
                        .setFromDate(LocalDate.of(762, 1, 1))
                        .setToDate(LocalDate.of(762, 12, 31)),
                new ResourceDTOInput()

                        .setName("The Art of War")
                        .setAuthor("Sun Tzu")
                        .setFromDate(LocalDate.of(-500, 1, 1))
                        .setToDate(LocalDate.of(-500, 12, 31)),
        };
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("InitData.run...");

        initUsers();

        // initFields();

        initResources();

        initUserSavedResources();
    }


    // --------------------- Init ---------------------

    void initUsers() {
        printCount(" before", "user", userService);

        Arrays.stream(getUsersInitData())
                .map(userMapper::convert)
                .forEach(userService::save);

        printCount(" after", "user", userService);
    }




    private void initFields(){
        // outcommented, since Users now instantiated with "root" field


//        Field testField1 = new Field().setName("TestField1").setUser(userRepository.findById(1L).orElse(null));
//        Field testField2 = new Field().setName("TestField2").setUser(userRepository.findById(2L).orElse(null));
//        Field testField3 = new Field().setName("TestField3").setUser(userRepository.findById(3L).orElse(null));

//        printCount(" before", "field", fieldService);
//        saveFields(testField1, testField2, testField3);
//        printCount(" after", "field", fieldService);
    }


    private void initResources() {

        // Saving the Test users and ressources
        printCount(" before", "resource", resourceService);
        Arrays.stream(getResourcesInitData())
                .map(resourceMapper::convert)
                .forEach(resourceService::save);
        printCount(" after", "resource", resourceService);
    }

    private void initUserSavedResources() {

    }


    // --------------------- Print ---------------------

    private void printCount(String message, String type, BaseService service) {
        int count = service.findAll().size();

        System.out.println(" - "+message+", total "+type+" saved: " + count);
    }



}