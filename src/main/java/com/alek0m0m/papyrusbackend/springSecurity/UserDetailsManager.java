package com.alek0m0m.papyrusbackend.springSecurity;

import org.springframework.security.core.userdetails.UserDetails;

//The UserDetailsManager interface extends the UserDetailService interface and provides
// additional methods for managing user details. It includes methods to
// create, update, delete users, change passwords, and check if a user exists.

public interface UserDetailsManager extends UserDetailService {

    void createUser(UserDetails userDetails);

    void updateUser(UserDetails userDetails);

    void deleteUser(String email);

    void changePassword(String oldPassword, String newPassword);

    boolean userExists(String email);
}