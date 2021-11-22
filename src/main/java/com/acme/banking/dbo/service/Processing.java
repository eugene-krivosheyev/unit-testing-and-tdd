package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.ArrayList;
import java.util.Collection;

public class Processing {
    private AccountRepository accounts;
    private ClientRepository clients;
    private Cash cash;


    /**
     * DI
     */
    public Processing(AccountRepository accounts, ClientRepository clients, Cash cash) {
        this.accounts = accounts;
        this.clients = clients;
        this.cash = cash;
    }

    public Client createClient(String name) {
        return clients.add(name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        //....
        return accounts.getAccountsByClientId(clientId);
        //....
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account from = accounts.getAccountById(fromAccountId);
        Account to = accounts.getAccountById(toAccountId);

        from.setAmount( from.getAmount() - amount );
        to.setAmount( to.getAmount() + amount );

        accounts.save(from);
        accounts.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}