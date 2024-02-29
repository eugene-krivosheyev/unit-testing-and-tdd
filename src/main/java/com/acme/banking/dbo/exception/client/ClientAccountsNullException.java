package com.acme.banking.dbo.exception.client;

public class ClientAccountsNullException extends IllegalArgumentException {
    public ClientAccountsNullException(String message) {
        super(message);
    }
}
