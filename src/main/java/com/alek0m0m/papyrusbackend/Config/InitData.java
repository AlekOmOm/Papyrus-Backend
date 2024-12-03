package com.alek0m0m.papyrusbackend.Config;

import com.alek0m0m.papyrusbackend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private UserRessourceRepository userRessourceRepository;

    @Override
    public void run(String... args) throws Exception {


        //Test users
        User TestUser1 = new User("Per","PerMail.com","1234","User");
        User TestUser2 = new User("Lars","LarsMail.com","888","User");
        User TestUser3 = new User("Franciska","FraciskaMail.com","MinKatsNavn","User");

        //Test ressources
        Ressource Testressource1 = new Ressource("Ressource1");
        Ressource Testressource2 = new Ressource("Ressource2");
        Ressource Testressource3 = new Ressource("Ressource3");

        //Saving the Test users and ressources
        ressourceRepository.save(Testressource1);
        ressourceRepository.save(Testressource2);
        ressourceRepository.save(Testressource3);
        ressourceRepository.save(TestUser1);
        ressourceRepository.save(TestUser2);
        ressourceRepository.save(TestUser3);


        TestUser1.addRessource(Testressource1);
        TestUser1.addRessource(Testressource2);
        TestUser2.addRessource(Testressource2);
        TestUser3.addRessource(Testressource3);

        ressourceRepository.save(Testressource1);
        ressourceRepository.save(Testressource2);
        ressourceRepository.save(Testressource3);
    }
}
