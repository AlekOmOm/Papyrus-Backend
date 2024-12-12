package com.alek0m0m.papyrusbackend.springSecurity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

//Authorities: Provides the roles or permissions granted to the user.
//Password: Retrieves the user's password.
//Email: Retrieves the user's email.
//Account Status: Checks if the account is expired, locked, credentials
//are expired, and if the account is enabled.

//This class is being ussed in UserDetailServiceImpl.java
//So the no usage is fake.

public interface UserDetails extends Serializable {

    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();
    String getEmail();

    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();



}