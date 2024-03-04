package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;

public class Processing {

    private final ClientRepository clientRepository;

    private final AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        return clientRepository.save(new Client(clientRepository.nextId(), name));
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return clientRepository.getById(clientId).getAccounts();
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        var from = accountRepository.getById(fromAccountId);
        var to = accountRepository.getById(toAccountId);
        from.withdraw(amount);
        to.deposit(amount);
        accountRepository.save(from);
        accountRepository.save(to);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
