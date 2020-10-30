package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {

    private AccountRepository repository;

    public Processing(AccountRepository repository){
        this.repository = repository;
    }

    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account accFrom = repository.findById(fromAccountId);
        Account accTo = repository.findById(toAccountId);
        accTo.setAmount(accTo.getAmount() + amount);
        accFrom.setAmount(accFrom.getAmount() - amount);
        repository.save(accFrom);
        repository.save(accTo);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
