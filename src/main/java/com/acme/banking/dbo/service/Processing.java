package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountNotFoundException;
import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.dao.ClientNotFoundException;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    private ClientRepository clientRepo;
    private AccountRepository accountRepo;

    public Processing(ClientRepository clientRepo, AccountRepository accountRepo) {
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
    }

    public UUID createClient(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("name is null or is empty");
        Client client = new Client(null, name);
        return clientRepo.add(client);
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) throws ClientNotFoundException {
        return clientRepo.findById(clientId).getAccounts();
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) throws AccountNotFoundException, NotEnoughMoneyException {
        Account fromAccount = accountRepo.findById(fromAccountId);
        Account toAccount = accountRepo.findById(toAccountId);
        if (fromAccount.getAmount()<amount) throw new NotEnoughMoneyException("Not enough money!");
        fromAccount.setAmount(fromAccount.getAmount()-amount);
        toAccount.setAmount(toAccount.getAmount() + amount);

        accountRepo.update(fromAccount);
        accountRepo.update(toAccount);
    }

    public void cash(double amount, UUID fromAccountId, Cash cash) throws AccountNotFoundException, NotEnoughMoneyException {
        if (cash == null) throw new IllegalArgumentException("cash is null");
        Account fromAccount = accountRepo.findById(fromAccountId);
        if (fromAccount.getAmount()<amount) throw new NotEnoughMoneyException("Not enough money!");
        fromAccount.setAmount(fromAccount.getAmount() - amount);
        accountRepo.update(fromAccount);

        cash.log(amount, fromAccountId);
    }
}
