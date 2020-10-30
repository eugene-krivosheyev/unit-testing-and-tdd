package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private AccountRepository repository;
    private ClientRepository clientRepository;

    public Processing(AccountRepository repository, ClientRepository clientRepository) {
        if (repository == null) throw new IllegalArgumentException();
        if (clientRepository == null) throw new IllegalArgumentException();
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    public UUID createClient(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException();
        return clientRepository.createClient(name);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        if (clientId == null) throw new IllegalArgumentException();
        return repository.getAllAccountsByClientId(clientId);
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        if (fromAccountId == null) throw new IllegalArgumentException();
        if (toAccountId == null) throw new IllegalArgumentException();

        Account from = repository.findById(fromAccountId);
        Account to = repository.findById(toAccountId);

        if (from == null) throw new IllegalStateException();
        if (to == null) throw new IllegalStateException();

        from.withdraw(amount);
        to.deposit(amount);

    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}