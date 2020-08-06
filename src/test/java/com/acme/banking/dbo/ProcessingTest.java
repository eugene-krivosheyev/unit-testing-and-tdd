package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static com.sun.tools.doclint.Entity.times;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldWithdrawAndDepositWhenTransfer() {
        final Processing sut = new Processing();
        final Account fromAccountMock = mock(Account.class);
        final Account toAccountMock = mock(Account.class);

        sut.transfer(1., fromAccountMock, toAccountMock);

        verify(fromAccountMock, times(1)).withdraw(1.);
        verify(toAccountMock).deposit(anyDouble());
    }
}
