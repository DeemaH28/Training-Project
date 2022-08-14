package com.example.app.exception;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String message){
        super(message);
    }
}
