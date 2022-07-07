package com.acme.banking.dbo.domain.service;

import com.acme.banking.dbo.domain.domain.Cash;
import com.acme.banking.dbo.domain.domain.Account;
import com.acme.banking.dbo.domain.domain.Client;

import java.util.Collection;

public class Processing {
    public Client createClient(String name) {
        return null; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
