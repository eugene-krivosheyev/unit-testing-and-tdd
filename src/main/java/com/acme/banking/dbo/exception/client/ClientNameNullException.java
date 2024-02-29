package com.acme.banking.dbo.exception.client;

public class ClientNameNullException extends IllegalArgumentException {
    public ClientNameNullException(String message) {
        super(message);
    }
}
