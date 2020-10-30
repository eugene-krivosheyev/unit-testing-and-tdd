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

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account from = accounts.findByID(fromAccountId);
        Account to = accounts.findByID(toAccountId);

        from.withDraw(amount);
        to.deposit(amount);

        accounts.save(from);
        accounts.save(to);


    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
