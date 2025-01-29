package com.springboot.first_spring_boot.shoppers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//The repository works with the Shopper class that has an id of type int
//The repository is the data layer
@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Integer>{
    //Writing business logic
}
