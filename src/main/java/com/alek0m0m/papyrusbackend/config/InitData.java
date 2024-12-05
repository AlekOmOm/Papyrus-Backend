package com.alek0m0m.papyrusbackend.config;

import com.Alek0m0m.library.spring.web.mvc.BaseService;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTOInput;
import com.alek0m0m.papyrusbackend.field.FieldRepository;
import com.alek0m0m.papyrusbackend.field.FieldService;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.alek0m0m.papyrusbackend.resource.ResourceDTOInput;
import com.alek0m0m.papyrusbackend.resource.ResourceRepository;
import com.alek0m0m.papyrusbackend.resource.ResourceService;
import com.alek0m0m.papyrusbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    public static UserDTOInput[] getUsersInitData() {
        return new UserDTOInput[]{
                new UserDTOInput()
                        .setName("name")
                        .setEmail("email")
                        .setPassword("password")
                        .setRole("user")
                        .setSavedResources(Arrays.stream(getResourcesInitData()).toList())
                        .setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[0])))),

                new UserDTOInput()
                        .setName("bob")
                        .setEmail("Bob@mail.com")
                        .setPassword("123")
                        .setRole("user")
                        .setSavedResources(Arrays.stream(getResourcesInitData()).toList())
                        .setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[1])))),
                new UserDTOInput()
                        .setName("Alice")
                        .setEmail("Alice@mail.com")
                        .setPassword("123")
                        .setRole("user")
                        .setSavedResources(Arrays.stream(getResourcesInitData()).toList())
                        .setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[2])))),

                new UserDTOInput()
                        .setName("Admin")
                        .setEmail("Admin@mail.com")
                        .setPassword("123")
                        .setRole("admin")
                        .setSavedResources(Arrays.stream(getResourcesInitData()).toList())
                        .setField(new FieldDTOInput(0L, "root", new ArrayList<>(List.of(getResourcesInitData()[3])))),

        };
    }

    public static ResourceDTOInput[] getResourcesInitData() {
        return new ResourceDTOInput[]{
                new ResourceDTOInput()
                        .setId(1L)
                        .setName("Metaphysics")
                        .setAuthor("Aristotle")
                        .setFromDate(LocalDate.of(350, 1, 1))
                        .setToDate(LocalDate.of(350, 12, 31)),
                new ResourceDTOInput()
                        .setId(2L)
                        .setName("The Republic")
                        .setAuthor("Plato")
                        .setFromDate(LocalDate.of(380, 1, 1))
                        .setToDate(LocalDate.of(380, 12, 31)),
                new ResourceDTOInput()
                        .setId(3L)
                        .setName("The Iliad")
                        .setAuthor("Homer")
                        .setFromDate(LocalDate.of(762, 1, 1))
                        .setToDate(LocalDate.of(762, 12, 31)),
                new ResourceDTOInput()
                        .setId(4L)
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

    }

    void initUsers() {
        printCount(" before", "user", userService);

        List<UserDTOInput> users = Arrays.stream(getUsersInitData()).toList();

        System.out.println("Users to save:");
            users.forEach(userDTOInput -> System.out.println(" - "+userDTOInput.getName()+", field and resources: "+userDTOInput.getField()+", "+userDTOInput.getSavedResources()));

        Arrays.stream(getUsersInitData())
                .map(userMapper::convert)
                .forEach(userService::save);

        printCount(" after", "user", userService);
    }





    // Makes fields one to one with user
    private void initFields(){
        // outcommented, since Users now instantiated with "root" field


//        Field testField1 = new Field().setName("TestField1").setUser(userRepository.findById(1L).orElse(null));
//        Field testField2 = new Field().setName("TestField2").setUser(userRepository.findById(2L).orElse(null));
//        Field testField3 = new Field().setName("TestField3").setUser(userRepository.findById(3L).orElse(null));

//        printCount(" before", "field", fieldService);
//        saveFields(testField1, testField2, testField3);
//        printCount(" after", "field", fieldService);
    }

    private void saveFields(Field... fields){
        for (Field field : fields) {
            fieldRepository.save(field);
        }
    }

    private void initResources() {
        // Test ressources
        Resource Testressource1 = new Resource()
                .setName("Ressource1").setAuthor("Author1").setFromDate(LocalDate.now()).setToDate((LocalDate.now().plusDays(1)));
        Resource Testressource2 = new Resource()
                .setName("Ressource2").setAuthor("Author2").setFromDate(LocalDate.now()).setToDate((LocalDate.now().plusDays(2)));
        Resource Testressource3 = new Resource()
                .setName("Ressource3").setAuthor("Author3").setFromDate(LocalDate.now()).setToDate((LocalDate.now().plusDays(3)));

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



}