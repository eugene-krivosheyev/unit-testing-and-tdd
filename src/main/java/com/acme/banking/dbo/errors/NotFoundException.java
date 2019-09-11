package com.acme.banking.dbo.errors;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
