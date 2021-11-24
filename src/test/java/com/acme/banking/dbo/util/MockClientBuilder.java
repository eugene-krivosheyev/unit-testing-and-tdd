package com.acme.banking.dbo.util;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class MockClientBuilder {

    private int id;
    private Set<Account> accounts = emptySet();
    private String name = "John Doe";

    public MockClientBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public MockClientBuilder withAccounts(Set<Account> accounts) {
        this.accounts = accounts;
        return this;
    }

    public MockClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Client build() {
        Client mock = mock(Client.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getAccounts()).thenReturn(accounts);
        when(mock.getName()).thenReturn(name);
        return mock;
    }
}