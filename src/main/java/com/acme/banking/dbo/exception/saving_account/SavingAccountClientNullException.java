package com.acme.banking.dbo.exception.saving_account;

public class SavingAccountClientNullException extends IllegalArgumentException {
    public SavingAccountClientNullException(String message) {
        super(message);
    }
}
