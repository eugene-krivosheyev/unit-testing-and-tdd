package com.acme.banking.dbo.exception.saving_account;

public class SavingAccountInvalidIdException extends IllegalArgumentException {
    public SavingAccountInvalidIdException(String message) {
        super(message);
    }
}
