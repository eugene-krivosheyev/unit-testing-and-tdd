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

    public Account createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(long clientId) {
        return null;
    }

    public void transfer(double amount, Account fromAccount, Account toAccount) {
        if (amount > fromAccount.getAmount()) throw new IllegalStateException("Недостаточно средств");

        fromAccount.sendMoney(amount);
        toAccount.getMoney(amount);


    }
    public void transfer(double amount, long fromAccountId, long toAccountId) {
       Account fromAccount = accounts.findById(fromAccountId);
        Account toAccount = accounts.findById(toAccountId);
        transfer(amount, fromAccount, toAccount);

    }

    public void cash(double amount, Account fromAccountId) {
        if (amount > fromAccountId.getAmount()) throw new IllegalStateException("Недостаточно средств");
        Cash.log(amount, fromAccountId);
    }
}
