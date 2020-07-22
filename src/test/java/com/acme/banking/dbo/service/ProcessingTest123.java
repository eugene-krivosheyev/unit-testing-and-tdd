package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.AccountRepository;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest123 {
    @Test @Ignore
    public void shouldWithdrawFromAndChargeToWhenTransfer() {
//    Given
        Processing sut = new Processing();
        SavingAccount mockFromAccount = mock(SavingAccount.class);
        when(mockFromAccount.getAmount()).thenReturn(100.);
        SavingAccount mockToAccount = mock(SavingAccount.class);
        Double amount = 100.0;

//    When
        sut.transfer(amount,mockFromAccount,mockToAccount);

//    Then
        verify(mockFromAccount, times(1)).withdraw(amount);
        verify(mockToAccount, times(1)).charge(amount);
    }

    @Test(expected = IllegalStateException.class) @Ignore
    public void shouldNotTransferWhenNotEnoughFunds() {
        Processing sut = new Processing();
        SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(0.);
        SavingAccount dummyAccount = mock(SavingAccount.class);
        Double amount = 1.0;

        sut.transfer(amount,stubAccount,dummyAccount);

    }

    @Test(expected = IllegalArgumentException.class) @Ignore
    public void shouldErrorWhenTransferAmountIsNegativeOrZero() {
        final Processing sut = new Processing();
        SavingAccount dummyAccount = mock(SavingAccount.class);
        Double amount = -1.0;

        sut.transfer(amount, dummyAccount, dummyAccount);

    }

    @Test @Ignore
    public void shouldAccountStateUpdatedWhenAccountsExistsInDB() {
        AccountRepository accounts = mock(AccountRepository.class);
        SavingAccount mockFromAccount = mock(SavingAccount.class);
        SavingAccount mockToAccount = mock(SavingAccount.class);
        when(mockFromAccount.getAmount()).thenReturn(100.);
        when(accounts.findById(0)).thenReturn(mockFromAccount);
        when(accounts.findById(1)).thenReturn(mockToAccount);
        final Processing sut = new Processing(accounts);

        sut.transfer(100.,0,1);

        verify(mockFromAccount).withdraw(100.);
        verify(mockToAccount).charge(100.);

    }

}
