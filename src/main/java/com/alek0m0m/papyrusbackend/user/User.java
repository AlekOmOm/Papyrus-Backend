package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@ToString

@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Field field;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Resource> savedResources = new ArrayList<>();

    public User () {
        this.field = new Field("root", new ArrayList<>());
    }


    // Setters (returning User)
    public User setId(long id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public User setField(Field field) {
        this.field = field;
        return this;
    }

    public User setSavedResources(List<Resource> savedResources) {
        this.savedResources = savedResources;
        return this;
    }


}
