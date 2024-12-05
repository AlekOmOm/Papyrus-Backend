
package com.alek0m0m.papyrusbackend.user;

import com.alek0m0m.papyrusbackend.field.FieldDTOInput;
import com.alek0m0m.papyrusbackend.resource.ResourceDTOInput;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@AllArgsConstructor
public class UserDTOInput {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;

    private FieldDTOInput field;

    private List<ResourceDTOInput> savedResources = new ArrayList<>();

    public UserDTOInput() {
        this.field = new FieldDTOInput()
                .setName("root");
    }


    // ------------------ Setters ------------------
    public UserDTOInput setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTOInput setName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOInput setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOInput setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTOInput setRole(String role) {
        this.role = role;
        return this;
    }

    public UserDTOInput setField(FieldDTOInput field) {
        this.field = field;
        return this;
    }

    public UserDTOInput setSavedResources(List<ResourceDTOInput> savedResources) {
        this.savedResources = savedResources;
        return this;
    }

}

