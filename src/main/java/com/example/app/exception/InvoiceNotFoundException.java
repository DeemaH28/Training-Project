package com.example.app.exception;

public class InvoiceNotFoundException extends Throwable {
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
