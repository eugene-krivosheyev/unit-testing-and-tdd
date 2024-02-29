package com.acme.banking.dbo.exception.client;

public class IllegalClientStateException extends IllegalArgumentException {
    public IllegalClientStateException(String message) {
        super(message);
    }
}
