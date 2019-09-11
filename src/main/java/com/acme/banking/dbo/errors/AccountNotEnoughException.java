package com.acme.banking.dbo.errors;

public class AccountNotEnoughException extends Exception {
    public AccountNotEnoughException(String message) {
        super(message);
    }
}
