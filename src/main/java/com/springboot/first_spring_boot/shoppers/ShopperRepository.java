package com.springboot.first_spring_boot.shoppers;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.first_spring_boot.books.Books;

//The repository works with the Shopper class that has an id of type int
//The repository is the data layer
@Repository
public interface ShopperRepository extends JpaRepository<Shopper, String>{
    //Writing business logic

    //Below statement is essentially this query: @Query("SELECT s FROM Shopper s WHERE s.email = ?1")
    Optional<Shopper> findShopperByEmail(String email);

    Shopper findByEmail(String email);

}
