package com.acme.banking.dbo.exception.saving_account;

public class IllegalSavingAccountIdArgumentException extends IllegalArgumentException {
    public IllegalSavingAccountIdArgumentException(String message) {
        super(message);
    }
}
