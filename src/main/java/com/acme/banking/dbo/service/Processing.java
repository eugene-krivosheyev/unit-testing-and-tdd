package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private AccountRepository accounts;

    public Processing() {
    }

    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, Account from, Account to) {
        if (from.getAmount() < amount) throw new IllegalStateException("Not enough funds");

        from.withdraw(amount);
        to.deposit(amount);
    }

    public void transfer(double amount, int fromAccountFromId, int fromAccountToId) {
        Account from = accounts.findById(fromAccountFromId);
        Account to = accounts.findById(fromAccountToId);

        transfer(amount, from, to);
    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
