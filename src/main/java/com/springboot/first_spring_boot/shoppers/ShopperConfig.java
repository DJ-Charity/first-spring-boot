package com.springboot.first_spring_boot.shoppers;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopperConfig {

    @Bean
    CommandLineRunner commandLineRunner(ShopperRepository repository) {
        return args -> {
            Shopper john = new Shopper("john@email.com", "john", LocalDate.of(2000, Month.JANUARY, 1));
            Shopper jane = new Shopper("jane@email.com", "jane", LocalDate.of(2010, Month.JULY, 10));

            //getting error when saving to databse
            repository.saveAll(List.of(john, jane));
        };
    }
    
}
