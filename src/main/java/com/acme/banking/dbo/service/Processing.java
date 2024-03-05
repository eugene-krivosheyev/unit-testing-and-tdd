package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;

public class Processing {
    private Cash cash;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Processing(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Processing(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Processing(Cash cash){
        this.cash = cash;
    }

    public Client createClient(String name) {
        Client client = new Client(name);
        return clientRepository.save(client);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        Client client = clientRepository.findClientById(clientId);
        return client.getAccounts();
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accountRepository.findByAccountId(fromAccountId);
        Account toAccount = accountRepository.findByAccountId(toAccountId);

        fromAccount.withDraw(amount);
        toAccount.deposit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}
