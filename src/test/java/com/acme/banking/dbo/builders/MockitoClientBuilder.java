package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoClientBuilder {
    private UUID id;
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public MockitoClientBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public MockitoClientBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public MockitoClientBuilder withAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public Client build() {
        Client clientMock = mock(Client.class);
        when(clientMock.getId()).thenReturn(id);
        when(clientMock.getName()).thenReturn(name);
        when(clientMock.getAccounts()).thenReturn(accounts);
        return clientMock;
    }
}
