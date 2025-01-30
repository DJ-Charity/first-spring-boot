package com.springboot.first_spring_boot.shoppers;


import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.*;

//These link the class to the table in database
@Entity
@Table
public class Shopper {
    @Id
    @SequenceGenerator(
        name = "shopper_sequence",
        sequenceName = "shopper_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "shopper_sequence"
    )
    Long id;

    //remember to verify email
    String email;
    String username;
    LocalDate dob;
    @Transient
    int age;

    public Shopper(){}
    
    public Shopper(String email, String username, LocalDate dob) {
        this.email = email;
        this.username = username;
        this.dob=dob;
    }

    public Shopper(Long id, String email, String username, LocalDate dob) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.dob=dob;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Shopper [id=" + id + ", email=" + email + ", username=" + username + "]";
    }

    
    
}
