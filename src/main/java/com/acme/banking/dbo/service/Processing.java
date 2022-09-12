package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.ClientRepository;

import java.util.Collection;

public class Processing {

    private Cash cash;
    private ClientRepository clientRepository;
    private Account accountRepository;

    public Processing(Cash cash, ClientRepository clientRepository, Account accountRepository) {
        this.cash = cash;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        int currentId = clientRepository.generateNextId();
        return new Client(currentId, name);
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return clientRepository.getAccounts(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account from = clientRepository.getAccountByClientId(fromAccountId);
        Account to = clientRepository.getAccountByClientId(toAccountId);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
        Account from = clientRepository.getAccountByClientId(fromAccountId);
        if (from.getAmount() < amount) {
          throw new RuntimeException("Недостаточно денег");
        }
        from.setAmount(from.getAmount() - amount);
        accountRepository.save(from);
    }
}
