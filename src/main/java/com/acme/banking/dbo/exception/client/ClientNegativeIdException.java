package com.acme.banking.dbo.exception.client;

public class ClientNegativeIdException extends IllegalArgumentException {
    public ClientNegativeIdException(String message) {
        super(message);
    }
}
