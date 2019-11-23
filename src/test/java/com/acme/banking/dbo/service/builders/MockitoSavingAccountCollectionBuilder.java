package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.ArrayList;
import java.util.Collection;

public class MockitoSavingAccountCollectionBuilder {
    private Collection<SavingAccount> stubAccount = new ArrayList<>();
    private int count = 0;

    public MockitoSavingAccountCollectionBuilder withSavingAccount(SavingAccount stubSavingAccount) {
        this.stubAccount.add(stubSavingAccount);
        return this;
    }

    public MockitoSavingAccountCollectionBuilder withCount(int count) {
        this.count = count;
        return this;
    }
    

    public Collection<SavingAccount> build() {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                stubAccount.add(new MockitoSavingAccountBuilder().build());
            }
        }
        return stubAccount;
    }
}
