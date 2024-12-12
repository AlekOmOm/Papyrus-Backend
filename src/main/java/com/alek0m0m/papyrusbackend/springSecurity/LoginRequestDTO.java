package com.alek0m0m.papyrusbackend.springSecurity;

import lombok.Getter;
import lombok.Setter;

//A simple DTO (Data Transfer Object) for encapsulating login credentials (email and password).
//Used in LoginController to map incoming JSON requests to Java objects.


@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
    private String name;
}
