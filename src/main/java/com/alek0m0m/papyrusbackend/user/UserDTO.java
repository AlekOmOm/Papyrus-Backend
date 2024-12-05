
package com.alek0m0m.papyrusbackend.user;


import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.alek0m0m.papyrusbackend.resource.ResourceDTO;
import lombok.*;

import java.util.List;

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
    @Getter
    private List<ResourceDTO> resources;

    @Override
    public User toEntity() {
        return new User()
                .setId(this.getId())
                .setName(this.getName())
                .setEmail(this.getEmail())
                .setPassword(this.getPassword())
                .setRole(this.getRole());
    }


    // Getters and Setters

    public UserDTO setId(long id) {
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

    public List<ResourceDTO> getResources() {
        return resources;
    }}
