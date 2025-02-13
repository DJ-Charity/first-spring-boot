package com.springboot.first_spring_boot.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;

    String getEmail() {
        return email;
    }
    String getPassword() {
        return password;
    }
}
