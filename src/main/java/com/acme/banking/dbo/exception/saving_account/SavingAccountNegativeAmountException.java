package com.acme.banking.dbo.exception.saving_account;

public class SavingAccountNegativeAmountException extends IllegalArgumentException {
    public SavingAccountNegativeAmountException(String message) {
        super(message);
    }
}
