package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoSavingAccountBuilder implements MockitoAccountBuilderInterface {
    private UUID id = UUID.randomUUID();
    private Client stubClient = new MockitoClientBuilder().build();
    private double amount = 0.;
    private SavingAccount stubAccount = mock(SavingAccount.class);


    public MockitoSavingAccountBuilder withOtherId() {
        when(stubAccount.getClient()).thenReturn(new MockitoClientBuilder().build());
        return this;
    }

    public MockitoSavingAccountBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public SavingAccount build() {
        when(stubAccount.getId()).thenReturn(id);
        when(stubAccount.getClient()).thenReturn(stubClient);
        when(stubAccount.getAmount()).thenReturn(amount);
        return stubAccount;
    }

    @Override
    public MockitoSavingAccountBuilder withAccount(Account stubAccount) {
        if (SavingAccount.class.equals(stubAccount.getClass())) this.stubAccount = (SavingAccount) stubAccount;
        return this;
    }
}
