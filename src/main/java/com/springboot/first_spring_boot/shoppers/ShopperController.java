package com.springboot.first_spring_boot.shoppers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shoppers")
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

    

    @GetMapping("/boughtbooks")
    public ResponseEntity<String> printShopperBooks(@RequestBody String shopperEmail) {
        return shopperService.printShopperBooks(shopperEmail);
    }

    /* 
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

    //use delete to delete resources from your system, like deleting a shopper(by id)
    @DeleteMapping(path = "{shopperId}")
    public void deleteShopper(@PathVariable("shopperId") Long shopperId) {
        shopperService.deleteShopper(shopperId);
    }

    //put is used to update resources in your system
    @PutMapping(path="{shopperId}")
    public void updateShopper(@PathVariable("shopperId") Long shopperId, @RequestParam(required = false) String firstname, @RequestParam(required = false)String email){
        //name and email are not required for this function(RequestParam)
        shopperService.updateShopper(shopperId, firstname, email);

    }*/
    
}
