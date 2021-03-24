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

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be positive");

        Account from = accounts.findById(fromAccountId);
        Account to = accounts.findById(toAccountId);

//        from.setAmount(from.getAmount() - amount);
//        to.setAmount(to.getAmount() + amount);

//        from.withdraw(amount);
//        to.deposit(amount);

        Account updatedFrom = from.withdraw(amount);
        Account updatedTo = to.deposit(amount);

        accounts.save(updatedFrom);
        accounts.save(updatedTo);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
