package com.acme.banking.dbo.exception.client;

public class ClientInvalidStateException extends IllegalArgumentException {
    public ClientInvalidStateException(String message) {
        super(message);
    }
}
