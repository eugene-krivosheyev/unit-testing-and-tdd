package com.acme.banking.dbo.exception.client;

public class IllegalClientIdArgumentException extends IllegalArgumentException {
    public IllegalClientIdArgumentException(String message) {
        super(message);
    }
}
