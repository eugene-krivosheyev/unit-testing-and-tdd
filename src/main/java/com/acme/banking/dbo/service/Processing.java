package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.dal.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private ClientRepository clients;
    private AccountRepository accounts;

    /**
     * Constructor DI
     */
    public Processing(ClientRepository clients, AccountRepository accounts) {
        this.clients = clients;
        this.accounts = accounts;
    }

    public UUID createClient(String name) {
        return clients.create(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        final Account account1 = accounts.findById(fromAccountId);
        final Account account2 = accounts.findById(toAccountId);

        account1.setAmount(account1.getAmount() - amount);
        account2.setAmount(account2.getAmount() + amount);

        accounts.save(account1);
        accounts.save(account2);
    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
