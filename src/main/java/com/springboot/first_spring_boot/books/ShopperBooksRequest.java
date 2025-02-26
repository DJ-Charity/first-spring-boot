package com.springboot.first_spring_boot.books;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopperBooksRequest {
    private String shopperEmail;

    public String getShopperEmail() {
        return shopperEmail;
    }
    
}
