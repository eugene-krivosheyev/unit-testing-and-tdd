package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProcessingTest {

    @Test
    public void transfer() {
        //region given
        Account fromAccountMock = mock(Account.class);
        Account toAccountMock = mock(Account.class);
        Processing sut = new Processing();
        double stubAmount = 1.0;
        //endregion

        //region when
        sut.transfer(stubAmount, fromAccountMock, toAccountMock);
        //endregion

        //region then
        verify(fromAccountMock, times(1)).withdraw(stubAmount);
        verify(toAccountMock, times(1)).deposit(stubAmount);
        //endregion
    }
}