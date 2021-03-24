package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {

    private AccountRepository accounts;

    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {

        if(amount <= 0) throw new IllegalArgumentException();
        if(fromAccountId == toAccountId) throw new IllegalArgumentException();

        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

//        from.setAmount(from.getAmount() - amount);
//        to.setAmount(to.getId() + amount);

        from.withdraw(amount);
        to.deposit(amount);

//        Account updateFrom = from.withdraw(amount);
//        Account updateTo = from.deposit(amount);

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
