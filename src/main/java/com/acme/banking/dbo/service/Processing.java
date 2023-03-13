package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Processing {
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        var client = new Client(1, name);
        return clientRepository.save(client);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        var client = clientRepository.finById(clientId);
        if (Objects.nonNull(client) && Objects.nonNull(client.getAccounts())) {
            return client.getAccounts();
        }
        return Collections.emptyList();
    }

    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        var accountFrom = accountRepository.findById(fromAccountId);
        var accountTo = accountRepository.findById(toAccountId);

        if (Objects.nonNull(accountFrom) && Objects.nonNull(accountTo)) {
            if (accountFrom.getAmount() >= amount) {
            accountFrom.withdraw(amount);
            accountTo.deposit(amount);
            accountRepository.save(accountFrom);
            accountRepository.save(accountTo);
            return true;
            }
        }
        return false;
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
