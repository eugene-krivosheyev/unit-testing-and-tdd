package com.acme.banking.dbo.service;

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

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

        from.withdraw(amount);
        to.deposit(amount);

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
