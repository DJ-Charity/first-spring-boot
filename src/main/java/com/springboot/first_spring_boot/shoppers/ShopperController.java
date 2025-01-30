package com.springboot.first_spring_boot.shoppers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/first")
public class ShopperController {
    //Controller is API layer and communicates to service layer

    private final ShopperService shopperService;
    
    //Autowired will auto initialize shopperService and inject it into this controller
    @Autowired
    public ShopperController(ShopperService shopperService) {
        //we use dependency injection as much as possible
        //so services should be injected into this controller constructor
        this.shopperService = shopperService;
    }

    @GetMapping
    public List<Shopper> printShopper() {
        return shopperService.printShopper();
    }

    //use post when you want to add new resources to your system, like adding a new shopper
    @PostMapping
    public void registerNewShopper(@RequestBody Shopper shopper) {
        //we take request body and put in into a new shopper
        //this leads to the service layer
        shopperService.addNewShopper(shopper);
    }

    @DeleteMapping(path = "{shopperId}")
    public void deleteShopper(@PathVariable("shopperId") Long shopperId) {
        shopperService.deleteShopper(shopperId);
    }
    
}
