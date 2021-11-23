package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class MockAccountBuilder {

    private int id;
    private double amount;
    private Client client = mock(Client.class);

    public MockAccountBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public MockAccountBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public MockAccountBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public Account build() {
        Account mock = mock(Account.class);
        when(mock.getId()).thenReturn(id);
        when(mock.getAmount()).thenReturn(amount);
        when(mock.getClient()).thenReturn(client);

        return mock;
    }
}