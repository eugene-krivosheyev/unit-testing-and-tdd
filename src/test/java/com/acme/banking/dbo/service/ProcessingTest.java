package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
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

    @Test(expected = IllegalStateException.class)
    public void shouldNotTransferWhenNotEnoughFunds() {
        Processing sut = new Processing();
        SavingAccount stubAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(0.);
        SavingAccount dummyAccount = mock(SavingAccount.class);
        Double amount = 1.0;

        sut.transfer(amount,stubAccount,dummyAccount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldErrorWhenTransferAmountIsNegativeOrZero() {
        final Processing sut = new Processing();
        SavingAccount dummyAccount = mock(SavingAccount.class);
        Double amount = -1.0;

        sut.transfer(amount, dummyAccount, dummyAccount);

    }

}
