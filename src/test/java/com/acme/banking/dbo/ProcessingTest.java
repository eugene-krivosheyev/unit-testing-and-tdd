package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    // given
    public void shouldAccountsStateUpdatedWhenTransfer() {
        Processing sut = new Processing();
        SavingAccount fromAccountMock = mock(SavingAccount.class);
        SavingAccount toAccountMock = mock(SavingAccount.class);
        double amount = 1.0;
        when(fromAccountMock.getAmount()).thenReturn(amount + 1);

// when
        sut.transfer(amount, fromAccountMock, toAccountMock);

// then
        verify(fromAccountMock, times(1)).sendMoney(amount);
        verify(toAccountMock, times(1)).getMoney(amount);
    }

    @Test (expected = IllegalStateException.class)
    public void shouldGetErrorWhenTransferMuchMoney() {
        Processing sut = new Processing();
        SavingAccount fromAccountMock = mock(SavingAccount.class);
        SavingAccount toAccountMock = mock(SavingAccount.class);
        //when(fromAccountMock.getAmount()).thenReturn(0.0);

        sut.transfer(100.0, fromAccountMock, toAccountMock);
    }

}
