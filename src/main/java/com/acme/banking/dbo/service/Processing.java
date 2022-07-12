package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public class Processing {
    private ClientRepository clientRepository;
    private AccountRepository accountsRepository;

    public Processing(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
    }

    public Client createClient(String name) {
        Client client = new Client(1, name);
        clientRepository.save(client);
        return client; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return accountsRepository.findClientById(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amount, int fromAccountId) {
        try {
            Cash.log(amount, fromAccountId);
        }
        catch (Exception e) {
            throw new IllegalStateException ("error cash.log");
        }
    }
}
