package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Test
    public void shoudUpdateAccountStateWhenTransfer() {
//        Given
        final Processing sut = new Processing();
        SavingAccount stubAccount = mock(SavingAccount.class);
        SavingAccount dummyAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(100.0);

//        When
        sut.transfer(1.0, stubAccount, dummyAccount);

//        Then
        verify(stubAccount, times(1)).withdraw(1.0);
        verify(dummyAccount, times(1)).charge(1.0);
    }
    @Test
    public void shoudUpdateAccountStateWhenTransferWithSpy() {
        Client dummyClient = mock(Client.class);
        SavingAccount spyFromAccount = spy(new SavingAccount(0L, dummyClient, 100.));
        SavingAccount spyToAccount = spy(new SavingAccount(1L, dummyClient, 0.));
        Processing sut = new Processing();

        sut.transfer(1.0, spyFromAccount, spyToAccount);

        assertEquals(99.0, spyFromAccount.getAmount(), 0.001);

        assertEquals(1.0, spyToAccount.getAmount(), 0.001);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldNotTransferWhenNotEnoughMoney() {
//        Given
        final Processing sut = new Processing();
        SavingAccount stubAccount = mock(SavingAccount.class);
        SavingAccount dummyAccount = mock(SavingAccount.class);
        when(stubAccount.getAmount()).thenReturn(100.0);

//        When
        sut.transfer(101.0, stubAccount, dummyAccount);

    }

    

}
