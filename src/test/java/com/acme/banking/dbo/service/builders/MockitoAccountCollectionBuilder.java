package com.acme.banking.dbo.service.builders;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.mock;

public class MockitoAccountCollectionBuilder {
    private Collection<Account> stubAccounts = new ArrayList<>();
    private Object accountClass = Client.class;
    private int count = 0;

    public MockitoAccountCollectionBuilder withAccount(Account stubAccount){
        accountClass = stubAccount.getClass();
        stubAccounts.add(stubAccount);
        return this;
    }
    public MockitoAccountCollectionBuilder withCount(int count){
        this.count = count;
        return this;
    }
    public MockitoAccountCollectionBuilder withAccountType(Object accountClass){
        this.accountClass = accountClass;
        return this;

    }
    private Account mockAccountWithAccountType() throws ClassNotFoundException {
        if (accountClass.equals(Client.class)) return new MockitoClientBuilder().build();
        if (accountClass.equals(SavingAccount.class)) return new MockitoSavingAccountBuilder().build();

        throw new ClassNotFoundException();
    }
    public Collection<Account> build () throws ClassNotFoundException {
        if (count>0) {
            for (int i = 0; i < count; i++) {
                stubAccounts.add(mockAccountWithAccountType());
            }
        }
        return  stubAccounts;
    }
}
