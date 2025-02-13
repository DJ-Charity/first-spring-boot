package com.springboot.first_spring_boot.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.first_spring_boot.config.JwtService;
import com.springboot.first_spring_boot.shoppers.Role;
import com.springboot.first_spring_boot.shoppers.Shopper;
import com.springboot.first_spring_boot.shoppers.ShopperRepository;

import lombok.RequiredArgsConstructor;

//service is responsible for registration and authorization implementation
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ShopperRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    //create new shopper in database and return generated token
    public AuthenticationResponse register(RegisterRequest request) {
        var shopper = Shopper.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .dob(request.getDob())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

        repository.save(shopper);

        var jwtToken = jwtService.generateToken(shopper);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       
        //authentication manager already has a authenticate method we can use
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        //at this point, user is authenticated
        var shopper = repository.findShopperByEmail(request.getEmail()).orElseThrow();

        //generate token with authorized user and return response with it
        var jwtToken = jwtService.generateToken(shopper);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
    
}
