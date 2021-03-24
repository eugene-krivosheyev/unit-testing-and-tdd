package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ProcessingTest {

    @Test
    void shouldTransferSuccessfully_whenAmountIsPositive() {
        AccountRepository accounts = mock(AccountRepository.class);
        Processing processing = new Processing(accounts);
        Account fromAccount = mock(Account.class);
        Account toAccount = mock(Account.class);
        Account updatedFrom = mock(Account.class);
        Account updatedTo = mock(Account.class);
        double amount = RandomUtils.nextDouble(0.01d, Double.MAX_VALUE);
        UUID fromAccountId = UUID.randomUUID();
        UUID toAccountId = UUID.randomUUID();
        when(accounts.findById(fromAccountId)).thenReturn(fromAccount);
        when(accounts.findById(toAccountId)).thenReturn(toAccount);
        when(fromAccount.withdraw(amount)).thenReturn(updatedFrom);
        when(toAccount.deposit(amount)).thenReturn(updatedTo);

        processing.transfer(amount, fromAccountId, toAccountId);

        verify(accounts).findById(fromAccountId);
        verify(accounts).findById(toAccountId);
        verify(fromAccount).withdraw(amount);
        verify(toAccount).deposit(amount);
        verify(accounts).save(updatedFrom);
        verify(accounts).save(updatedTo);
    }

    @Test
    void shouldReturnError_whenAmountIsZero() {
        AccountRepository accounts = mock(AccountRepository.class);
        Processing processing = new Processing(accounts);
        double amount = 0.0d;
        UUID fromAccountId = UUID.randomUUID();
        UUID toAccountId = UUID.randomUUID();

        assertThatThrownBy(() -> processing.transfer(amount, fromAccountId, toAccountId))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must be positive");

        verify(accounts, never()).findById(any(UUID.class));
        verify(accounts, never()).findById(any(UUID.class));
        verify(accounts, never()).save(any());
        verify(accounts, never()).save(any());
    }

    @Test
    void shouldReturnError_whenAmountIsNegative() {
        AccountRepository accounts = mock(AccountRepository.class);
        Processing processing = new Processing(accounts);
        double amount = -1.0d;
        UUID fromAccountId = UUID.randomUUID();
        UUID toAccountId = UUID.randomUUID();

        assertThatThrownBy(() -> processing.transfer(amount, fromAccountId, toAccountId))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must be positive");

        verify(accounts, never()).findById(any(UUID.class));
        verify(accounts, never()).findById(any(UUID.class));
        verify(accounts, never()).save(any());
        verify(accounts, never()).save(any());
    }
}