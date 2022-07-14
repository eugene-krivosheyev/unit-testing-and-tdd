package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;

import java.util.Collection;

public class Processing {

    private Cash cash;
    private AccountRepository accountRepository;

    public Processing(Cash cash, AccountRepository accountRepository) {
        this.cash = cash;
        this.accountRepository = accountRepository;
    }

    public Client createClient(String name) {
        return null; //TODO
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        return null; //TODO
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        Account accountFrom = accountRepository.findById(fromAccountId);
        Account accountTo = accountRepository.findById(toAccountId);

        accountFrom.debit(amount);
        accountTo.issue(amount);

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    public void cash(double amount, int fromAccountId) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount!");
        }
        Account accountFrom = accountRepository.findById(fromAccountId);
        if (accountFrom == null) {
            throw new IllegalArgumentException("Account not found!");
        }

        if (accountFrom.getAmount() < amount) {
            throw new IllegalStateException("Not enough amount!");
        }

        accountFrom.debit(amount);
        try {
            cash.log(amount, fromAccountId);
        }catch (Exception ex) {
            throw new IllegalStateException("Smth went wrong!");
        }

        accountRepository.save(accountFrom);
    }
}
