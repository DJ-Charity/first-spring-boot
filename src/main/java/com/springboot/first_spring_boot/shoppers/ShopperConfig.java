package com.springboot.first_spring_boot.shoppers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopperConfig {

    @Bean
    CommandLineRunner commandLineRunner(ShopperRepository repository) {
        return args -> {
            /* Test Code
            Shopper john = new Shopper("john@email.com", "john", null, LocalDate.of(2000, Month.JANUARY, 1));
            Shopper jane = new Shopper("jane@email.com", "jane", null, LocalDate.of(2010, Month.JULY, 10));

            repository.saveAll(List.of(john, jane));
            */
        };
    }
    
}
