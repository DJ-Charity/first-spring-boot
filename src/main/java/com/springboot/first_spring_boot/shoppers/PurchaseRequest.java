package com.springboot.first_spring_boot.shoppers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PurchaseRequest {
    String email;
    Long isbn;
    
}
