package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, Account fromAccount, Account toAccount) {
        if (amount <= 0. || amount > 1000_000_000. || fromAccount.getAmount() < amount)
            throw new IllegalArgumentException("Incorrect amount value");

        fromAccount.changeAmount(-amount);
        toAccount.changeAmount(amount);
    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
