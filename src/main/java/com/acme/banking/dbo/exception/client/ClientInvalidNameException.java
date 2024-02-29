package com.acme.banking.dbo.exception.client;

public class ClientInvalidNameException extends IllegalArgumentException {
    public ClientInvalidNameException(String message) {
        super(message);
    }
}
