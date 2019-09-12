package com.acme.banking.dbo.builders;

import com.acme.banking.dbo.domain.Account;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoAccountBuilder {
    private UUID id;
    private double amount;

    public MockitoAccountBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public MockitoAccountBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Account build() {
        Account accountMock = mock(Account.class);
        when(accountMock.getId()).thenReturn(id);
        when(accountMock.getAmount()).thenReturn(amount);
        return accountMock;
    }
}
