package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.AccountRepository;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoRepoBuilder {
    private Map<UUID, Account> accounts = new HashMap<>();

    public MockitoRepoBuilder withAccount(UUID accountId, double amount) {
        Account toAdd = mock(Account.class);
        when(toAdd.getId()).thenReturn(accountId);

        accounts.put(accountId, toAdd);
        return this;
    }

    public AccountRepository build() {
        AccountRepository repo = mock(AccountRepository.class);

        accounts.keySet().forEach(k -> {
            when(repo.findAccountById(k)).thenReturn(accounts.get(k));
        });

        return repo;
    }
}
