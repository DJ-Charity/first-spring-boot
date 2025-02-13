package com.springboot.first_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.first_spring_boot.shoppers.ShopperRepository;

import lombok.RequiredArgsConstructor;

//This class will hold all application configurations for easy bean injection
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    //the repository accesses the database
    private final ShopperRepository repository;

    //We need a bean for getting user details
    @Bean
    public UserDetailsService userDetailsService() {
        //we use a lambda here
        //it either finds the user or returns an exception
        return username -> repository.findShopperByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //we need a bean for the application provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //responsible for fetching user details and encoding passwords and such
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        //we provided the user detail service to use that will tell how to fetch data
        authProvider.setUserDetailsService(userDetailsService());
        //password encoder 
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }
    
    //authentication manager has methods to help authenticate user with username and password
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}