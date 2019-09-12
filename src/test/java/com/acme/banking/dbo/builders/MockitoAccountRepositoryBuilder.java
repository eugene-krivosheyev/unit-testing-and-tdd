package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.dao.AccountNotFoundException;
import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoAccountRepositoryBuilder {
    private Map<UUID, Account> uuidAccountMap = new HashMap<>();

    public MockitoAccountRepositoryBuilder withAccountById(UUID id, Account account) {
        uuidAccountMap.put(id, account);
        return this;
    }

    public AccountRepository build() throws AccountNotFoundException {
        AccountRepository accountRepoMock = mock(AccountRepository.class);
        for (Map.Entry<UUID, Account> entry : uuidAccountMap.entrySet()) {
            when(accountRepoMock.findById(entry.getKey())).thenReturn(entry.getValue());
        }
        return accountRepoMock;
    }
}
