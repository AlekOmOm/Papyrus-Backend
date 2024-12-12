package com.alek0m0m.papyrusbackend.springSecurity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//An interface extending UserDetailsService.
//Declares a method to load user details by email.

public interface UserDetailService extends org.springframework.security.core.userdetails.UserDetailsService {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
