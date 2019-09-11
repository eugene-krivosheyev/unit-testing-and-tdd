package com.acme.banking.dbo.errors;

public class AccountException extends Exception {
    public AccountException(String message) {
        super(message);
    }
}
