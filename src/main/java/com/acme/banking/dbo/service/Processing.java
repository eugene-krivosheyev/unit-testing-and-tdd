package com.acme.banking.dbo.service;

import com.acme.banking.dbo.repository.AccountRepository;


public class Processing {
    private AccountRepository accountRepository;
    private TransactionValidation validation;
    private CashLoggerProvider logger;

    public Processing(AccountRepository accountRepository, CashLoggerProvider logger) {
        this.accountRepository = accountRepository;
        this.logger = logger;
        this.validation = new TransactionValidation();
    }

    public void transfer(Integer fromAccountId, Integer toAccountId, double amount) {
        validation.validate(amount);
        accountRepository.getAccount(fromAccountId).changeBalance(-amount);
        if (toAccountId == null) {
            logger.log(amount, fromAccountId);
        } else {
            accountRepository.getAccount(toAccountId).changeBalance(amount);
        }
    }

}
