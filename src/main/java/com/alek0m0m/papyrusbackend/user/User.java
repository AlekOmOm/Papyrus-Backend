package com.alek0m0m.papyrusbackend.user;

import com.Alek0m0m.library.jpa.*;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@ToString

@AllArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;
    private String password;
    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Field field;


    @ManyToMany
    @JoinTable(
            name = "user_resource",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
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
