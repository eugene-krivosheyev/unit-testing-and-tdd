package com.acme.banking.dbo.domain;

import org.mockito.Mockito;

public class MockitoAccountBuilder {

    private int featureId = 1;
    private Client featureClient = Mockito.mock(Client.class);
    private Double featureAmount = 0.;

    public MockitoAccountBuilder withId(int id) {
        this.featureId = id;
        return this;
    }

    public MockitoAccountBuilder widthClient(Client client) {
        this.featureClient = client;
        return this;
    }

    public MockitoAccountBuilder withAmount(double amount) {
        this.featureAmount = amount;
        return this;
    }

    public Account build() {
        Account mockAccount = Mockito.mock(Account.class);
        Mockito.when(mockAccount.getId()).thenReturn(this.featureId);
        Mockito.when(mockAccount.getClient()).thenReturn(this.featureClient);
        Mockito.when(mockAccount.getAmount()).thenReturn(this.featureAmount);
        return mockAccount;
    }
}
