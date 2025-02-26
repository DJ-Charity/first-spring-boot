package com.springboot.first_spring_boot.books;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    private Long isbn;

    private String title;
    private String author;
    private BigDecimal price;
    private Genre genre;

    @Builder.Default
    @OneToMany(mappedBy="book")
    Set<ShopperBooks> shopperBooks = new HashSet<>();

    public Books(Long isbn, String title, String author, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }


    public Long getIsbn() {
        return isbn;
    }
    public void setIsbn(Long iSBN) {
        this.isbn = iSBN;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Set<ShopperBooks> getShopperBooks() {
        return shopperBooks;
    }
    public void setShopperBooks(Set<ShopperBooks> shopperBooks) {
        this.shopperBooks = shopperBooks;
    }
      
}
