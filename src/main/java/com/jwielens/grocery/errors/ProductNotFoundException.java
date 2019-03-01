package com.jwielens.grocery.errors;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id)  {
        super("Kan product niet vinden: " + id);
    }
}
