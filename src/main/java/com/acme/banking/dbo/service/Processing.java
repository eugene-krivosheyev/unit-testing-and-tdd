package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repositories.AccountRepository;
import com.acme.banking.dbo.repositories.ClientRepository;

import java.util.Collection;

import static java.util.Objects.requireNonNull;

public class Processing {

    private final Cash cash;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;

    public Processing(Cash cash, ClientRepository clientRepository, AccountRepository accountRepository) {
        this.cash = requireNonNull(cash, "Parameter 'cash' must not be null");
        this.clientRepository = requireNonNull(clientRepository, "Parameter 'clientRepository' must not be null");
        this.accountRepository = requireNonNull(accountRepository, "Parameter 'accountRepository' must not be null");
    }

    public Client createClient(String name) {
        return clientRepository.saveClient(new Client(name));
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return clientRepository.getAccountsByClientId(clientId);
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account sender = accountRepository.getAccountById(fromAccountId);
        Account receiver = accountRepository.getAccountById(toAccountId);

        sender.setAmount(sender.getAmount() - amount);
        receiver.setAmount(receiver.getAmount() + amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
    }

    public void cash(double amount, int fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}
