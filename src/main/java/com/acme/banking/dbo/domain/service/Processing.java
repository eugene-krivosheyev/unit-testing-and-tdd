package com.acme.banking.dbo.domain.service;

import com.acme.banking.dbo.domain.domain.Cash;
import com.acme.banking.dbo.domain.domain.Account;
import com.acme.banking.dbo.domain.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;

import java.util.Collection;

public class Processing {


    private AccountRepository repository;

    public Processing(AccountRepository repository) {

        this.repository = repository;
    }

    public Client createClient(String name) {
        return null; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amount, int fromAccountId) {
        Account account = repository.getAccountById(fromAccountId);
        Cash.log(amount, fromAccountId);
    }
}
