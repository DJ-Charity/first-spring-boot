package com.springboot.first_spring_boot.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//we need to provide endpoints for shoppers to create and authenticate their account
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    
    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterRequest request, HttpServletResponse response) {
        //request holds info needed to authorize user
        return service.register(request, response);
    }
    /*public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest request) {
        //request holds info needed to make new account
        return ResponseEntity.ok(service.register(request));
    }*/

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.OPTIONS, RequestMethod.POST})
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate (@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        //request holds info needed to authorize user
        return service.authenticate(request, response);
    }
    /*public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        //request holds info needed to authorize user
        ResponseEntity<AuthenticationResponse> authResponse = ResponseEntity.ok(service.authenticate(request, response));
        authResponse.header(HttpHeaders.SET_COOKIE, authResponse.getBody().getCookie().toString());

        return authResponse;
    }*/
    
    
}
