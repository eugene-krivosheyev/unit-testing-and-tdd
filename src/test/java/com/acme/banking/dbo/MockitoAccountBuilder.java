package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoAccountBuilder {
    private int futureId;
    private double futureAmount;

    private Client futureClient;

    public MockitoAccountBuilder withId(int id) {
        futureId = id;
        return this;
    }

    public MockitoAccountBuilder withAmount(double amount) {
    futureAmount = amount;
    return this;
    }

    public MockitoAccountBuilder withClient(Client client) {
        futureClient = client;
        return this;
    }

    public Account build() {
        Account accountStub = mock(Account.class);
        when(accountStub.getId()).thenReturn(futureId);
        when(accountStub.getAmount()).thenReturn(futureAmount);
        when(accountStub.getClient()).thenReturn(futureClient);
        return accountStub;
    }
}
