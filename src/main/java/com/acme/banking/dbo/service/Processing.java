package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

public class Processing {
    private AccountRepository accounts;

    public Processing() {
    }

    public Processing(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public long createClient(String name) {
        return 0;
    }

    public void transfer(double amount, Account from, Account to) {
        if (from==null) throw new IllegalStateException("from account is null");
        if (to==null) throw new IllegalStateException("to account is null");
        if (from.getAmount() < amount) throw new IllegalStateException("Not enough money");

        from.withdraw(amount);
        to.deposit(amount);
    }

    public void transfer(double amount, long fromAccountId, long toAccountId) {
        if (fromAccountId<0) throw new IllegalStateException("account id 'from' less than zero");
        if (toAccountId<0) throw new IllegalStateException("account id 'to' less than zero");

        Account fromAccount = accounts.findById(fromAccountId);
        Account toAccount = accounts.findById(toAccountId);

        transfer(amount, fromAccount, toAccount);
    }

    public void cash(double amount, long fromAccountId) {
        Cash.log(amount, accounts.findById(fromAccountId));
    }
}
