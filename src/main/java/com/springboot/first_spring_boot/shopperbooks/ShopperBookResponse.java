package com.springboot.first_spring_boot.shopperbooks;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopperBookResponse {
    Set<ShopperBooks> purchaseBooks;
    
}
