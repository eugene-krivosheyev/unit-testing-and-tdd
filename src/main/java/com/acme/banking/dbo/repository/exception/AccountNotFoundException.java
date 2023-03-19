package com.acme.banking.dbo.repository.exception;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {
    public AccountNotFoundException(int accountId) {
        super(String.format("Account with id = %d is not found", accountId));
    }
}
