package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    public Integer createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(Integer clientId) {
        return null;
    }

    public void transfer(double amount, Account fromAccount, Account toAccount) {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }

    public void cash(double amount, Integer fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
