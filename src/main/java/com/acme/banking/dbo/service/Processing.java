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

    public Processing(ClientRepository clientRepository, AccountRepository accountRepository){
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        Client client = new Client(clientRepository.getLastClientId() + 1, name);
        clientRepository.saveClient(client);
        return client;
    }

    public Collection<Account> getAccountsByClientId(int clientId)
    {
        Client client = clientRepository.getClientById(clientId);
        if(client == null)
            throw new IllegalArgumentException("Client does not exist");
        return client.getAccounts();
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account accountFrom = accountRepository.findAccountById(fromAccountId);
        Account accountTo = accountRepository.findAccountById(toAccountId);
        if(accountFrom == null)
            throw new IllegalArgumentException("AccountFrom does not exist");
        if(accountTo == null)
            throw new IllegalArgumentException("AccountTo does not exist");
        if(accountFrom.getAmount() - amount < 0)
            throw new IllegalArgumentException("Not enough amount in accountFrom");
        accountFrom.setAmount(accountFrom.getAmount() - amount);
        accountTo.setAmount(accountTo.getAmount() + amount);
        accountRepository.saveAccount(accountFrom);
        accountRepository.saveAccount(accountTo);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
