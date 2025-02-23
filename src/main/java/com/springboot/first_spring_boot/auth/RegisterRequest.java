package com.springboot.first_spring_boot.auth;

import java.time.LocalDate;
import com.springboot.first_spring_boot.shoppers.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname; 
    private String email;
    private String password;
    private LocalDate dob;
    private Role role;
}
