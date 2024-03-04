package com.acme.banking.dbo.repository.impl;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repository.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    private static final Map<Integer, Account> storedAccounts = new HashMap<>();

    @Override
    public Account registerAccount(Account account) {
        storedAccounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Optional<Account> getAccount(int accountId) {
        return Optional.ofNullable(storedAccounts.getOrDefault(accountId, null));
    }
}
