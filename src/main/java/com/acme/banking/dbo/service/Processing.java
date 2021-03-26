package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.Collections;

public class Processing {
    private AccountRepository accounts;

    //DI: constructor
    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Client createClient(String name) {
        return new Client(accounts.getLastId() + 1, name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return Collections.singleton(accounts.findById(clientId));
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) throw new IllegalArgumentException();

        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);

//        from.withdraw(amount);
//        to.deposit(amount);

//        Account updatedFrom = from.withdraw(amount);
//        Account updatedTo = from.deposit(amount);

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
