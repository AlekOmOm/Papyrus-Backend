package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;
import com.alek0m0m.papyrusbackend.Ressource.Ressource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private String role;


    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Ressource> ressources = new ArrayList<>();


    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;

    }
}
