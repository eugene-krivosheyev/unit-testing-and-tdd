package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;

import java.util.Collection;

public class Processing {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public Client createClient(String name) {
        Client client = new Client(name);
        return clientRepository.save(client);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        Client client = clientRepository.getById(clientId);
        return client.getAccounts();
    }

    //@Transactional
    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accountRepository.getById(fromAccountId);
        Account toAccount = accountRepository.getById(toAccountId);
        fromAccount.minusAmount(amount);
        toAccount.plusAmount(amount);
    }

    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
