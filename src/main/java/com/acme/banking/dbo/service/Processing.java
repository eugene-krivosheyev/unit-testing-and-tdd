package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repo.AccountRepository;

import java.util.Collection;
import java.util.UUID;

public class Processing {

    private AccountRepository accountRepository;

    public Processing(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UUID createClient(String name) {
        return null;
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {

    }

    public void cash(double amount, UUID fromAccountId) {
        Account account = accountRepository.findById(fromAccountId);

        account.withdraw(amount);

        accountRepository.save(account);
    }
}
