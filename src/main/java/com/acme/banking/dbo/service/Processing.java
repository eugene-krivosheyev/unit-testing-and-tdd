package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.CashInternalLogger;
import com.acme.banking.dbo.repository.AccountRepository;


public class Processing {
    private AccountRepository accountRepository;
    private TransactionValidation validation;

    public Processing(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.validation = new TransactionValidation();
    }

    public void transfer(Integer fromAccountId, Integer toAccountId, double amount) {
        validation.validate(amount);
        accountRepository.getAccount(fromAccountId).changeBalance(-amount);
        if (toAccountId == null) {
            CashInternalLogger.log(amount, fromAccountId);
        } else {
            accountRepository.getAccount(toAccountId).changeBalance(amount);
        }
    }

}
