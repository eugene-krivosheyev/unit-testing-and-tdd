package com.acme.banking.dbo.error;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
