package com.springboot.first_spring_boot.books;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table
@IdClass(ShopperBookKey.class)
public class ShopperBooks{
    @Id
    private Long shopperID;
    @Id
    private Long bookIsbn; 

    public ShopperBooks() {
    }
    
    public ShopperBooks(Long shopperID, Long bookIsbn) {
        this.shopperID = shopperID;
        this.bookIsbn = bookIsbn;
    }

    public Long getShopperID() {
        return shopperID;
    }
    public void setShopperID(Long shopperID) {
        this.shopperID = shopperID;
    }
    public Long getBookIsbn() {
        return bookIsbn;
    }
    public void setBookIsbn(Long bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
    
}
