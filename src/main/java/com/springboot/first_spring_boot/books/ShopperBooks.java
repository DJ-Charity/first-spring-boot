package com.springboot.first_spring_boot.books;

import java.io.Serializable;

import com.springboot.first_spring_boot.shoppers.Shopper;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table
@IdClass(ShopperBookKey.class)
public class ShopperBooks{

    @Id
    private String shopperEmail;
    @Id
    private Long bookIsbn;

    @ManyToOne
    @JoinColumn(name = "email")
    private Shopper shopper;

    @ManyToOne //failing here
    /*Failed to initialize JPA EntityManagerFactory: Property 'com.springboot.first_spring_boot.books.ShopperBooks.bookIsbn' 
    belongs to an '@IdClass' but has no matching property in entity class 'com.springboot.first_spring_boot.books.ShopperBooks' 
    (every property of the '@IdClass' must have a corresponding persistent property in the '@Entity' class) */
    @JoinColumn(name = "isbn")
    private Books book;

    public ShopperBooks() {
    }

    public ShopperBooks(Shopper shopper, Books book) {
        this.shopper = shopper;
        this.book = book;
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

    
    
}
