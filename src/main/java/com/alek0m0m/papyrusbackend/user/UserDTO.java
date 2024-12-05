
package com.alek0m0m.papyrusbackend.user;


import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.field.Field;
import com.alek0m0m.papyrusbackend.field.FieldDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Version;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseEntityDTO<User> {

    private String name;
    private String email;
    private String password;
    private String role;

    private FieldDTO field;

    private List<ResourceDTO> savedResources = new ArrayList<>();


    @Version
    private int version;



    // ----------------- Constructors -----------------
    public UserDTO(User input) {
        this.setId(input.getId() != 0 ? input.getId() : 0L);
        this.name = input.getName();
        this.email = input.getEmail();
        this.password = input.getPassword();
        this.role = input.getRole();
        this.field = new FieldDTO(input.getField());
        this.savedResources = input.getSavedResources().stream()
                .map(ResourceDTO::new).toList();
        this.version = input.getVersion();
    }

    public UserDTO(UserDTOInput input) {
        this.setId(input.getId() != null ? input.getId() : 0L);
        this.name = input.getName();
        this.email = input.getEmail();
        this.password = input.getPassword();
        this.role = input.getRole();
        this.field = new FieldDTO(input.getField());
        this.savedResources = input.getSavedResources().stream()
                .map(ResourceDTO::new).toList();

    }

    // ----------------- Mapper logic  -----------------
    @Override
    public User toEntity() {
        User user = new User()
                .setId(this.getId() != 0 ? this.getId() : 0L)
                .setName(this.getName())
                .setEmail(this.getEmail())
                .setPassword(this.getPassword())
                .setRole(this.getRole())
                .setField(this.getField().toEntity())
                .setSavedResources(this.getSavedResources().stream()
                        .map(ResourceDTO::toEntity).toList());
        user.setVersion(this.version);
        return user;
    }



    // ----------------- Business Operations -----------------






    // ----------------- Getters and Setters -----------------

    public UserDTO setId(Long id) {
        super.setId(id);
        return this;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public UserDTO setField(FieldDTO field) {
        this.field = field;
        return this;
    }

    public UserDTO setSavedResources(List<ResourceDTO> savedResources) {
        this.savedResources = savedResources;
        return this;
    }

}
