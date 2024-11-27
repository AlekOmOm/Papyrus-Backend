package com.alek0m0m.papyrusbackend.user;

import jakarta.persistence.Entity;
import lombok.*;



@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOInput {

    private long id;
    private String name;
    private String email;
    private String password;
    private String role;


    // Getters and Setters



    public UserDTOInput setId(long id) {
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

}
