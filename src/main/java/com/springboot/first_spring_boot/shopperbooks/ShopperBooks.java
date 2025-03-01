package com.springboot.first_spring_boot.shopperbooks;

import java.time.LocalDate;

import com.springboot.first_spring_boot.books.Books;
import com.springboot.first_spring_boot.shoppers.Shopper;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

//ShopperBooks creates the relationship between Shoppers and the Books they purchase
@Entity
@Table
public class ShopperBooks{

    @EmbeddedId
    ShopperBookKey id = new ShopperBookKey();

    @ManyToOne
    @MapsId("shopperEmail")
    @JoinColumn(name = "shopperEmail", referencedColumnName = "email")
    private Shopper shopper;

    @ManyToOne
    @MapsId("bookIsbn") 
    @JoinColumn(name = "bookIsbn", referencedColumnName = "isbn")
    private Books book;

    private LocalDate purchaseDate;  

    public ShopperBooks() {
    }

    public ShopperBooks(Shopper shopper, Books book, LocalDate purchaseDate) {
        this.shopper = shopper;
        this.book = book;
        this.purchaseDate = purchaseDate;
    }


    public Shopper getShopper() {
        return shopper;
    }

    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
    
    public ShopperBookKey getId() {
        return id;
    }

    public void setId(ShopperBookKey id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
