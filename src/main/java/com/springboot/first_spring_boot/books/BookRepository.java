package com.springboot.first_spring_boot.books;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books, Long>{
    Optional<Books> findById(Long bookIsbn);
    
} 
