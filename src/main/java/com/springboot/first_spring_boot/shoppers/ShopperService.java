package com.springboot.first_spring_boot.shoppers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;


//This annotation specifies that this class is a service class
@Service
public class ShopperService {
    //This is the service layer and it communicates to the data access layer...like a database

    private ShopperRepository shopperRepository;

    @Autowired
    public ShopperService(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }
    
    
    public List<Shopper> printShopper() {
        return shopperRepository.findAll();
    }

    public void addNewShopper(Shopper shopper) {
        Optional<Shopper> shopperByEmail = shopperRepository.findShopperByEmail(shopper.getEmail());

        //checks if email is already taken by user
        if(shopperByEmail.isPresent()) {
            //should eventually make custom exception
            throw new IllegalStateException("Email exists");
        }

        //check if email is valid

        //save new shopper
        shopperRepository.save(shopper);
        System.out.println(shopper);

    }


    public void deleteShopper(Long shopperId) {
        
        boolean exists = shopperRepository.existsById(shopperId);
        if(!exists) {
            throw new IllegalStateException("Student with id " + shopperId + " does not exist");
        }

        shopperRepository.deleteById(shopperId);
    }
    
}
