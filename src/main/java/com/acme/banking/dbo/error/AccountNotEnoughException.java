package com.acme.banking.dbo.error;

public class AccountNotEnoughException extends Exception {
    public AccountNotEnoughException(String message) {
        super(message);
    }
}
