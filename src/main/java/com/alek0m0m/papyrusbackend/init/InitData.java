package com.alek0m0m.papyrusbackend.init;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InitData implements CommandLineRunner {

    private final UserDTOInput[] users = {
        new UserDTOInput(0, "name", "email", "password", "role"),
        new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
        new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
        new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public InitData(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        initUsers();
        printInit();
        printUsers();
    }

    private void printInit() {
        System.out.println("Init data:");
        Arrays.stream(users).forEach(System.out::println);
    }

    private void printUsers() {
        System.out.println("printUsers:");
        //userService.findAll().forEach(System.out::println);

        int count = userService.findAll().size();

        System.out.println("Total users saved: " + count);
    }


    void initUsers() {
        Arrays.stream(users).map(userMapper::convert).forEach(userService::save);

    }




}
