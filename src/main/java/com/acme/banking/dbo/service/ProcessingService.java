package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;

import java.util.Collection;
import java.util.UUID;

public class ProcessingService {
    private AccountRepository accountRepository;
    private Cash cash;

    public ProcessingService(AccountRepository accountRepository, Cash cash) {
        this.accountRepository = accountRepository;
        this.cash = cash;
    }

    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {
        Account from = accountRepository.findAccountById(fromAccountId);
        Account to = accountRepository.findAccountById(toAccountId);
        from.withdraw(amount);
        to.credit(amount);
        accountRepository.save(from);
        accountRepository.save(to);
    }

    public void cash(double amount, UUID fromAccountId) {
        cash.log(amount, fromAccountId);
    }
}
