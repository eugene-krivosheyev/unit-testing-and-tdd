package com.acme.banking.dbo.service;

import com.acme.banking.dbo.exception.OddTransactionValidationException;

public class TransactionValidation {

    public void validate(double amount){
        if (amount % 2 == 0){
            throw new OddTransactionValidationException();
        }
    }
}
