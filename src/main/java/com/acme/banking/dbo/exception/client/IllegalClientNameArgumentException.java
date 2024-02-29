package com.acme.banking.dbo.exception.client;

public class IllegalClientNameArgumentException extends IllegalArgumentException {
    public IllegalClientNameArgumentException(String message) {
        super(message);
    }
}
