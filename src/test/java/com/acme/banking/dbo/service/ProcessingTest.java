package com.acme.banking.dbo.service;

import com.acme.banking.dbo.MockitoAccountBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessingTest {

    @Test
    void shouldCallCorrectMethodsWhenTrasfer() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        Account accountToStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(repoStub.findById(2)).thenReturn(accountToStub);

        sut.transfer(1, 2, 1000.0);


        assertAll("Should call correct methods when transfer",
                () -> verify(accountFromStub, times(1)).debit(1000.0),
                () -> verify(accountToStub, times(1)).issue(1000.0),
                () -> verify(repoStub, times(1)).save(accountFromStub),
                () -> verify(repoStub, times(1)).save(accountToStub)
        );
    }

    @Test
    void shouldCallCorrectMethodsWhenCash() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);

        sut.cash(1000.0, 1);

        assertAll("Cash test",
                () -> verify(accountFromStub, times(1)).debit(1000.0),
                () -> verify(repoStub, times(1)).save(accountFromStub),
                () -> verify(cashStub, times(1)).log(1000.0, 1)
        );
    }
}