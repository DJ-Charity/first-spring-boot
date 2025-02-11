package com.springboot.first_spring_boot.shoppers;


import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//These link the class to the table in database
@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shopper implements UserDetails{

      //implements UserDetails so that it is a spring user
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
      private String email;
      private String firstname;
      private String lastname;
      private String password;

      private LocalDate dob;
      @Transient
      private int age;

      @Enumerated(EnumType.STRING)
      private Role role;
  
      public Shopper(String email, String firstname, String password, LocalDate dob) {
          this.email = email;
          this.firstname = firstname;
          this.password = password;
          this.dob=dob;
      }
  
      public Shopper(Long id, String email, String firstname, String password, LocalDate dob) {
          this.id = id;
          this.email = email;
          this.firstname = firstname;
          this.password = password;
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
  
      public String getFirstname() {
          return firstname;
      }
      public void setFirstname(String firstname) {
          this.firstname = firstname;
      }

      public String getLastname() {
          return lastname;
      }
      public void setLastname(String lastname) {
          this.lastname = lastname;
      }

      @Override
      public String getUsername() {
           return email;
      }
  
      @Override
      public String getPassword() {
          return password;
      }
      public void setPassword(String password) {
          this.password = password;
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

      public Role getRole() {
        return role;
      }
      public void setRole(Role role) {
        this.role = role;
      }
  
      @Override
      public String toString() {
          return "Shopper [id=" + id + ", email=" + email + ", firstname=" + firstname + "]";
      }
  
      //Should return a list of Roles that the user can have
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
          return(List.of(new SimpleGrantedAuthority(role.name())));
      } 
     
}
