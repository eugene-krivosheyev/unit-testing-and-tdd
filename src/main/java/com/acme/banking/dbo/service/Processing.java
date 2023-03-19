package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.repository.exception.AccountNotFoundException;

import java.util.Collection;

public class Processing {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(Client newClient) {
        return clientRepository.save(newClient);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return accountRepository.findAllByClientId(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {

        Account from = accountRepository.findByAccountId(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException(fromAccountId));

        Account to = accountRepository.findByAccountId(toAccountId)
                .orElseThrow(() ->  new AccountNotFoundException(toAccountId));

        from.withdraw(amount);
        to.deposit(amount);

        accountRepository.save(from);
        accountRepository.save(to);

    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
