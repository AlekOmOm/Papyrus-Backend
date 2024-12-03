package com.alek0m0m.papyrusbackend.config.initOld;

import com.alek0m0m.papyrusbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitDataOld implements CommandLineRunner {

    private final UserDTOInput[] users = {
        new UserDTOInput(0, "name", "email", "password", "role"),
        new UserDTOInput(0, "Bob", "Bob@mail.com", "123", "user"),
        new UserDTOInput(0, "Alice", "Alice@mail.com", "123", "user"),
        new UserDTOInput(0, "Admin", "Admin@mail.com", "123", "admin")
    };

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public InitDataOld(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("InitData.run...");

        // initUsers();
//        printInit();
//        printUsers();
    }

    private void printInit() {
        System.out.println("Init data:");

        Arrays.stream(users).forEach(System.out::println);

    }

    private void printUsers() {
        System.out.println("printUsers:");
        //userService.findAll().forEach(System.out::println);

    }

    private void printCount(String message) {
        int count = userService.findAll().size();

        System.out.println(" - "+message+", total users saved: " + count);
    }


    void initUsers() {
        printCount(" before");
        Arrays.stream(users)
                .map(userMapper::convert)
                .forEach(userService::save);
        printCount(" after");
    }




}
