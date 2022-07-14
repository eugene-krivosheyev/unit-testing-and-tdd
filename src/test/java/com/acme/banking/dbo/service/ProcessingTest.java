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
        when(accountFromStub.getAmount()).thenReturn(2.0);
        int stubAccountId1 = 1;
        int stubAccountId2 = 2;
        double dummyAmount = 1.0;

        sut.transfer(stubAccountId1, stubAccountId2, dummyAmount);


        assertAll("Should call correct methods when transfer",
                () -> verify(accountFromStub, times(1)).debit(1.0),
                () -> verify(accountToStub, times(1)).issue(1.0),
                () -> verify(repoStub, times(1)).save(accountFromStub),
                () -> verify(repoStub, times(1)).save(accountToStub)
        );
    }

    @Test
    void shouldNotTransferWhenInvalidAmount() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        Account accountToStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(repoStub.findById(2)).thenReturn(accountToStub);
        int stubAccountId1 = 1;
        int stubAccountId2 = 2;
        double invalidAmount = 0.0;

        assertThrows(IllegalArgumentException.class,
                () -> sut.transfer(stubAccountId1, stubAccountId2, invalidAmount));
    }

    @Test
    void shouldNotTransferWhenOneOfAccountsNotFound() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        int stubAccountId1 = 1;
        int stubAccountId2 = 2;
        double dummyAmount = 1.0;

        assertThrows(IllegalArgumentException.class,
                () -> sut.transfer(stubAccountId1, stubAccountId2, dummyAmount));
    }

    @Test
    void shouldNotTransferWhenNotEnoughAmount() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        Account accountToStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(repoStub.findById(2)).thenReturn(accountToStub);
        int stubAccountId1 = 1;
        int stubAccountId2 = 2;
        double stubAmount = 1.0;

        assertThrows(IllegalStateException.class,
                () -> sut.transfer(stubAccountId1, stubAccountId2, stubAmount));
    }

    @Test
    void shouldCallCorrectMethodsWhenCash() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(accountFromStub.getAmount()).thenReturn(2.0);


        sut.cash(1.0, 1);

        assertAll("Cash test",
                () -> verify(accountFromStub, times(1)).debit(1.0),
                () -> verify(repoStub, times(1)).save(accountFromStub),
                () -> verify(cashStub, times(1)).log(1.0, 1)
        );
    }

    @Test
    void shouldNotThrowExceptionWhenCash() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(accountFromStub.getAmount()).thenReturn(2.0);
        double dummyAmount = 1.0;
        int dummyAccountId = 1;

        assertDoesNotThrow(() -> sut.cash(dummyAmount, dummyAccountId));
    }

    @Test
    void shouldNotCashWhenInvalidAmount() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        int dummyAccountId = 1;
        double invalidAmount = 0.0;

        assertThrows(IllegalArgumentException.class,
                () -> sut.cash(invalidAmount, dummyAccountId));
    }

    @Test
    void shouldNotCashWhenAccountNotFound() {
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        int stubAccountId = 1;
        double dummyAmount = 1.0;

        assertThrows(IllegalArgumentException.class,
                () -> sut.cash(dummyAmount, stubAccountId));
    }

    @Test
    void shouldNotCashWhenNotEnoughAmount() {
        Account accountFromStub = new MockitoAccountBuilder().build();
        AccountRepository repoStub = mock(AccountRepository.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(cashStub, repoStub);
        when(repoStub.findById(1)).thenReturn(accountFromStub);
        when(accountFromStub.getAmount()).thenReturn(0.0);
        int stubAccountId = 1;
        double stubAmount = 1.0;

        assertThrows(IllegalStateException.class,
                () -> sut.cash(stubAmount, stubAccountId));
    }
}