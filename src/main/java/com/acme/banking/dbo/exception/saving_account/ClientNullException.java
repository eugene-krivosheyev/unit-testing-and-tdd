package com.acme.banking.dbo.exception.saving_account;

public class ClientNullException extends IllegalArgumentException {
    public ClientNullException(String message) {
        super(message);
    }
}
