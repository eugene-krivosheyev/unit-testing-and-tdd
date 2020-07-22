package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest {
    private Processing sut = new Processing();
    private final SavingAccount mockToAccount = mock(SavingAccount.class);
    private final SavingAccount mockFromAccount = mock(SavingAccount.class);
    private final double fromAmount = 200.;
    private final double poorAmount = 0.;
    private final double transferAmount = 100.;
    private final int fromAccountId = 0;
    private final int toAccountId = 1;
    private final int defectAccountId = -1;

    @Test
    public void shouldAccountsStateUpdatedWhenTransfer() {
        when(mockFromAccount.getAmount()).thenReturn(fromAmount);

        sut.transfer(transferAmount, mockFromAccount, mockToAccount);

        verify(mockFromAccount, times(1)).withdraw(transferAmount);
        verify(mockToAccount).deposit(anyDouble());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        final SavingAccount dummyAccount = mock(SavingAccount.class);
        when(mockFromAccount.getAmount()).thenReturn(poorAmount);

        sut.transfer(transferAmount, mockFromAccount, dummyAccount);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenFromAccountIsNull() {
        sut.transfer(transferAmount, null, mockToAccount);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenToAccountIsNull() {
        sut.transfer(transferAmount, mockFromAccount, null);
    }

    @Test
    public void shouldAccountStateUpdatedWhenAccountExistsInDb() {
        AccountRepository accounts = mock(AccountRepository.class);
        sut = new Processing(accounts);
        when(mockFromAccount.getAmount()).thenReturn(fromAmount);
        when(accounts.findById(fromAccountId)).thenReturn(mockFromAccount);
        when(accounts.findById(toAccountId)).thenReturn(mockToAccount);

        sut.transfer(transferAmount, fromAccountId, toAccountId);

        verify(mockFromAccount, times(toAccountId)).withdraw(transferAmount);
        verify(mockToAccount).deposit(anyDouble());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenFromAccountIdLessThanZero() {
        sut.transfer(transferAmount, defectAccountId, toAccountId);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenToAccountIdLessThanZero() {
        sut.transfer(transferAmount, fromAccountId, defectAccountId);
    }
}