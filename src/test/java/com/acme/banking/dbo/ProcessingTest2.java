package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProcessingTest2 {
    @Test(expected = IllegalStateException.class)
    public void shouldGetExceptionWhenNotEnouthMoney() {
        Processing processing = new Processing();
        Account to = mock(Account.class);
        Account from = mock(Account.class);
        when(from.getAmount()).thenReturn(1.);
        processing.transfer(100.0d, from, to);
    }

    @Test
    public void shouldStatesAccounsWillBeUpdatedWhenEnouthMoney() {
        Processing processing = new Processing();
        Account to = mock(Account.class);
        Account from = spy(Account.class);
        when(from.getAmount()).thenReturn(100.);

        processing.transfer(100.0d, from, to);

        verify(from).withdraw(100.);
        verify(to).deposit(100.);
    }

    @Test
    public void shouldStatesAccounsWillBeUpdatedWhenEnouthMoney2() {
        Processing processing = new Processing();
        Account from = mock(Account.class);
//        when(from.findById());
        // не успел доделать
        processing.transfer(100., 1, 2);
//        verify

    }
}
