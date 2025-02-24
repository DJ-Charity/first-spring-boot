package com.springboot.first_spring_boot.books;

import java.io.Serializable;

public class ShopperBookKey implements Serializable{
    private Long shopperID;
    private Long bookIsbn;

    public ShopperBookKey() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shopperID == null) ? 0 : shopperID.hashCode());
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
        if (shopperID == null) {
            if (other.shopperID != null)
                return false;
        } else if (!shopperID.equals(other.shopperID))
            return false;
        if (bookIsbn == null) {
            if (other.bookIsbn != null)
                return false;
        } else if (!bookIsbn.equals(other.bookIsbn))
            return false;
        return true;
    }
}
