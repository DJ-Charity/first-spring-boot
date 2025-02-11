package com.springboot.first_spring_boot.shoppers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;


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
            throw new IllegalStateException("Shopper with id " + shopperId + " does not exist");
        }

        shopperRepository.deleteById(shopperId);
    }

    //transactional uses setters we get to see if we can update the resource
    @Transactional
    public void updateShopper(Long shopperId, String firstname, String email) {
        //Verify if shopper exists
        Shopper shopper = shopperRepository.findById(shopperId)
            .orElseThrow(() -> new IllegalStateException("Shopper with id " + shopperId + " does not exist"));

        //Check if firstname is valid then updates
        if(firstname != null && firstname.length() > 0 && !Objects.equals(shopper.getFirstname(), firstname)) {
            shopper.setFirstname(firstname);
        }

        //Check if email is valid then updates
        if(email != null && email.length() > 0 && !Objects.equals(shopper.getEmail(), email)) {
            //Check if email is already taken
            Optional<Shopper> shopperOptional = shopperRepository.findShopperByEmail(email);
            if(shopperOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }

            //If not, update
            shopper.setEmail(email);
        }
    }

    
    
}
