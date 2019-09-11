package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.dal.ClientNotFoundException;
import com.acme.banking.dbo.dal.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class Processing {
    private ClientRepository clients;
    private AccountRepository accounts;

    public Processing(ClientRepository clients) {
        this.clients = clients;
    }

    public UUID createClient(String name) {
        Client client = new Client(UUID.randomUUID(), name);
        clients.saveClient(client.getId(), client.getName());
        return client.getId();
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) throws ClientNotFoundException {
        return clients.findByClientId(clientId).getAccounts();
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) throws AccountNotFoundException {
        Account fromAccount = accounts.findById(fromAccountId);
        Account toAccount = accounts.findById(toAccountId);

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);
    }



    public void cash(double amount, UUID fromAccountId) throws AccountNotFoundException, IllegalArgumentException {
        Account fromAccount = accounts.findById(fromAccountId);
        if (fromAccount.getAmount() > amount) {
            fromAccount.setAmount(fromAccount.getAmount() - amount);
            Cash.log(amount, fromAccountId);
        }
        throw new IllegalArgumentException("You cannot cash more than account amount");
    }
}
