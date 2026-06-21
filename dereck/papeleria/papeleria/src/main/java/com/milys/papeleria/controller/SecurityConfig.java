package com.milys.papeleria.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$8IVEULUevRQ8ed9Hdp1hFeUdH0OlfhVAZt/VLC1Vs7vUBwu1rM9wO")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}