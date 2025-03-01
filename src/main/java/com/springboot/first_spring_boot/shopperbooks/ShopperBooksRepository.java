package com.springboot.first_spring_boot.shopperbooks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopperBooksRepository extends JpaRepository<ShopperBooks, ShopperBookKey> {
    //Optional<ShopperBooks> findBooksByEmail(String email);

    
} 