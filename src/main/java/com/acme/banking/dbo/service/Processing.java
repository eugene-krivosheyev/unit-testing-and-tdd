package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    public Account createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(Long clientId) {
        return null;
    }

    public void transfer(double amount, Account fromAccountId, Account toAccountId) {
        fromAccountId.withdraw(amount);
        toAccountId.charge(amount);
    }

    public void cash(double amount, UUID fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
