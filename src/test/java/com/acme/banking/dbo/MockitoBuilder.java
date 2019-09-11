package com.acme.banking.dbo;

import com.acme.banking.dbo.dal.AccountRepo;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class MockitoBuilder {
    private AccountRepo repoStub;
    private Cash cashStub;

    public MockitoBuilder createRepoMock(){
        this.repoStub = mock(AccountRepo.class);
        return this;
    }

    public MockitoBuilder setFindAccountByIdWhenThen(UUID id, SavingAccount account){
        when(repoStub.findAccountById(id)).thenReturn(account);
        return this;
    }

    public AccountRepo buildAccountRepoMock(){
        return this.repoStub;
    }

    public MockitoBuilder createCashMock(){
        this.cashStub = mock(Cash.class);
        return this;
    }

    public Cash buildCashMock(){
        return this.cashStub;
    }

    public Processing buildProcessing(){
        return new Processing(this.repoStub,this.cashStub);
    }

    public Processing buildProcessingSpy(){
        return spy(new Processing(this.repoStub,this.cashStub));
    }
}
