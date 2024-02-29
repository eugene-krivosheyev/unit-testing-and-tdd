package com.acme.banking.dbo.exception.saving_account;

public class IllegalSavingAccountAmountArgumentException extends IllegalArgumentException {
    public IllegalSavingAccountAmountArgumentException(String message) {
        super(message);
    }
}
