package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.CashInternalLogger;
import com.acme.banking.dbo.repository.AccountRepository;

import java.math.BigDecimal;

public class Processing {

    private AccountRepository accountRepository;
    public void transfer(Integer fromAccountId, Integer toAccountId, double amount) {
        accountRepository.getAccount(fromAccountId).changeBalance(-amount);
        if (toAccountId == null) {
            CashInternalLogger.log(amount, fromAccountId);
        } else {
            accountRepository.getAccount(toAccountId).changeBalance(amount);
        }
    }

}
