package com.springboot.first_spring_boot.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopperBooksRepository extends JpaRepository<ShopperBooks, ShopperBookKey> {
    //Optional<ShopperBooks> findBooksByEmail(String email);

    
} 