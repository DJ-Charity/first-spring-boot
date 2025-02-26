package com.springboot.first_spring_boot.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first_spring_boot.shoppers.ShopperService;

@RestController
@RequestMapping("/api/v1/shopperbooks")
public class ShopperBooksController {
    private final ShopperBooksService service;

    @Autowired
    public ShopperBooksController(ShopperBooksService service) {
        this.service = service;
    }
    
}
