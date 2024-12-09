package com.alek0m0m.papyrusbackend.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Configuration public class ProjectSecurityConfig {

        @Bean
        // Bean Makes this method as a prouction method that will be callen by spring
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
            //HTTP configuration for the project
            http.authorizeHttpRequests((requests) -> {

                requests.requestMatchers("/myAccount","/myBalance").authenticated()
                        // point thta the following request should be authenticated

                        .requestMatchers("/contact").permitAll();
                // Specifies which requests should be permitted without authentication

            });

            // Enables form-based login with default settings
            http.formLogin(Customizer.withDefaults());
            // Enables HTTP Basic authentication with default settings
            http.httpBasic(Customizer.withDefaults());

            // Builds and returns the SecurityFilterChain
            return http.build(); }

    }


}
