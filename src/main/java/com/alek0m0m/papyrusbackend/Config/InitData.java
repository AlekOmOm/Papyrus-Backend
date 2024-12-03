package com.alek0m0m.papyrusbackend.Config;

import com.alek0m0m.papyrusbackend.Ressource.Ressource;
import com.alek0m0m.papyrusbackend.Ressource.RessourceRepository;
import com.alek0m0m.papyrusbackend.user.User;
import com.alek0m0m.papyrusbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Test users
        User TestUser1 = new User("Per", "PerMail.com", "1234", "User");
        User TestUser2 = new User("Lars", "LarsMail.com", "888", "User");
        User TestUser3 = new User("Franciska", "FraciskaMail.com", "MinKatsNavn", "User");

        // Test ressources
        Ressource Testressource1 = new Ressource("Ressource1", "Author1", LocalDate.now(), LocalDate.now().plusDays(1));
        Ressource Testressource2 = new Ressource("Ressource2", "Author2", LocalDate.now(), LocalDate.now().plusDays(2));
        Ressource Testressource3 = new Ressource("Ressource3", "Author3", LocalDate.now(), LocalDate.now().plusDays(3));

        // Saving the Test users and ressources
        ressourceRepository.save(Testressource1);
        ressourceRepository.save(Testressource2);
        ressourceRepository.save(Testressource3);
        userRepository.save(TestUser1);
        userRepository.save(TestUser2);
        userRepository.save(TestUser3);

    }
}