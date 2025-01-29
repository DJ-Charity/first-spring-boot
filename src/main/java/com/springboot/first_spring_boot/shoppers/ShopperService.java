package com.springboot.first_spring_boot.shoppers;

import java.util.List;

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
        System.out.println(shopper);

    }
    
}
