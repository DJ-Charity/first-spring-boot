package com.springboot.first_spring_boot.shoppers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import com.springboot.first_spring_boot.books.BookRepository;
import com.springboot.first_spring_boot.books.Books;
import com.springboot.first_spring_boot.shopperbooks.ShopperBookResponse;
import com.springboot.first_spring_boot.shopperbooks.ShopperBooks;


//This annotation specifies that this class is a service class
@Service
public class ShopperService {
    //This is the service layer and it communicates to the data access layer...like a database

    private ShopperRepository shopperRepository;
    private BookRepository bookRepository;

    @Autowired
    public ShopperService(ShopperRepository shopperRepository, BookRepository bookRepository) {
        this.shopperRepository = shopperRepository;
        this.bookRepository = bookRepository;
    }

    public ShopperBookResponse printShopperBooks(String shopperEmail) {
        
        Shopper shopper = shopperRepository.findByEmail(shopperEmail);
        Set<ShopperBooks> boughtBooks = shopper.getShopperBooks();

        ShopperBookResponse responseEntity = ShopperBookResponse.builder().purchaseBooks(boughtBooks).build();

        return responseEntity;
    }

    public String purchaseBook(PurchaseRequest request) {
        Shopper shopper = shopperRepository.findByEmail(request.getEmail());
        Optional<Books> book = bookRepository.findById(request.getIsbn());

        shopper.getShopperBooks().add(new ShopperBooks(shopper, book.get(), LocalDate.now()));

        return "Book purchased";

    }

    public List<Books> printAllBooks(String request) {
        return bookRepository.findAll();
    }
    
   /*  public List<Shopper> printShopper() {
        return shopperRepository.findAll();
    }

    public void addNewShopper(Shopper shopper) {
        Optional<Shopper> shopperByEmail = shopperRepository.findShopperByEmail(shopper.getEmail());

        //checks if email is already taken by user
        if(shopperByEmail.isPresent()) {
            //should eventually make custom exception
            throw new IllegalStateException("Email exists");
        }

        //check if email is valid

        //save new shopper
        shopperRepository.save(shopper);
        System.out.println(shopper);
    }

    public void deleteShopper(Long shopperId) {
        
        boolean exists = shopperRepository.existsById(shopperId);
        if(!exists) {
            throw new IllegalStateException("Shopper with id " + shopperId + " does not exist");
        }

        shopperRepository.deleteById(shopperId);
    }

    //transactional uses setters we get to see if we can update the resource
    @Transactional
    public void updateShopper(Long shopperId, String firstname, String email) {
        //Verify if shopper exists
        Shopper shopper = shopperRepository.findById(shopperId)
            .orElseThrow(() -> new IllegalStateException("Shopper with id " + shopperId + " does not exist"));

        //Check if firstname is valid then updates
        if(firstname != null && firstname.length() > 0 && !Objects.equals(shopper.getFirstname(), firstname)) {
            shopper.setFirstname(firstname);
        }

        //Check if email is valid then updates
        if(email != null && email.length() > 0 && !Objects.equals(shopper.getEmail(), email)) {
            //Check if email is already taken
            Optional<Shopper> shopperOptional = shopperRepository.findShopperByEmail(email);
            if(shopperOptional.isPresent()) {
                throw new IllegalStateException("Email is taken");
            }

            //If not, update
            shopper.setEmail(email);
        }
    }*/

}
