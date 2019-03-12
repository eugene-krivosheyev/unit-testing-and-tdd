package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private AccountRepository repository;
    private ClientRepository clientRepository;

    public Processing(AccountRepository repository, ClientRepository clientRepository) {
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    public UUID createClient(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        return clientRepository.createClient(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        if (clientId == null) throw new IllegalArgumentException();
        return repository.getAccountsByClientId(clientId);
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {

    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
