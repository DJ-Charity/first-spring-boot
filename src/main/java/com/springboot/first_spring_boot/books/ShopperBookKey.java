package com.springboot.first_spring_boot.books;

import java.io.Serializable;

public class ShopperBookKey implements Serializable{
    private String shopperEmail;
    private Long bookIsbn;

    public ShopperBookKey() {
    }

    public String getShopperEmail() {
        return shopperEmail;
    }

    public void setShopperEmail(String shopperEmail) {
        this.shopperEmail = shopperEmail;
    }

    public Long getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(Long bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shopperEmail == null) ? 0 : shopperEmail.hashCode());
        result = prime * result + ((bookIsbn == null) ? 0 : bookIsbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShopperBookKey other = (ShopperBookKey) obj;
        if (shopperEmail == null) {
            if (other.shopperEmail != null)
                return false;
        } else if (!shopperEmail.equals(other.shopperEmail))
            return false;
        if (bookIsbn == null) {
            if (other.bookIsbn != null)
                return false;
        } else if (!bookIsbn.equals(other.bookIsbn))
            return false;
        return true;
    }

    
}
