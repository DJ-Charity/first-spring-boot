package com.springboot.first_spring_boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import lombok.RequiredArgsConstructor;

//Security configuration is seen at startup
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
        
            //responsible for configuring http security for app
            @Bean
            public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                .csrf(csrf -> csrf.disable()) //disable csrf
                .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/api/v1/auth/**").permitAll() //whitelists the matched url that can give http requests
                    .anyRequest().authenticated()) //any other request needs to be authenticated
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//the session should be stateless(no storing it) so each request will be authenticated and have its own session
                .authenticationProvider(authenticationProvider) //what authentication provider to use
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //now we use the filter we made; we want to use this filter before username and password filter so security context is already updated


        return http.build();

    }

}
