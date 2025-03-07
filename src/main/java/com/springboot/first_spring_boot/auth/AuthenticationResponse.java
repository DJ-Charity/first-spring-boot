package com.springboot.first_spring_boot.auth;

import org.springframework.http.ResponseCookie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private ResponseCookie cookie;

}
