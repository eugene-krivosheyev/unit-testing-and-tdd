package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;


// http://192.168.83.148
public class Processing {
    private AccountRepository repository;
    private ClientRepository clientRepository;

    public Processing(AccountRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    //TODO: implement and cover with _unit_ test
    public UUID createClient(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        return clientRepository.createClient(name);
    }

    //TODO: implement getNonNegativeAccountsByClientId and cover with _unit_ tests
    public Collection<Account> getAccountsByClientId(UUID clientId) {
        if (clientId == null) throw new IllegalArgumentException();
        return repository.getAllAccountsByClientId(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
