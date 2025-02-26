package com.springboot.first_spring_boot.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ShopperBooksService {

    private ShopperBooksRepository repository;
    @Autowired
    public ShopperBooksService(ShopperBooksRepository repository) {
        this.repository = repository;
    }


}
