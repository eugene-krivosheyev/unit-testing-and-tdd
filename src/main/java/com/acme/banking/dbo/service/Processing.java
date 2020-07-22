package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.AccountRepository;
import com.acme.banking.dbo.domain.Cash;
import com.sun.org.apache.bcel.internal.generic.IADD;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private AccountRepository accounts;

    public Processing() {}

    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public Account createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(Long clientId) {
        return null;
    }

    public void transfer(double amount, Account fromAccount, Account toAccount) {
        if(fromAccount.getAmount() < amount) throw new IllegalStateException("Not enough funds!");
        if(amount <= 0) throw new IllegalArgumentException("Zero or negative amount!");
        if(fromAccount == null || toAccount == null) throw new IllegalArgumentException("Null accounts!");
        fromAccount.withdraw(amount);
        toAccount.charge(amount);
    }

    public void transfer(double amount, int fromAccountId, int toAccountId) {
        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

        transfer(amount, from, to);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
