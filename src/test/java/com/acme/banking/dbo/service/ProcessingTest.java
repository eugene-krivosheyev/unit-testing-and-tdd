package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldAccountsChangeStatesWhenTransfer() {
        final Account mockFromAccount = mock(Account.class);
        final Account mockToAccount = mock(Account.class);
        final Processing sut = new Processing();
        when(mockFromAccount.getAmount()).thenReturn(1.);

        sut.transfer(1., mockFromAccount, mockToAccount);

        verify(mockFromAccount).changeAmount(-1.);
        verify(mockToAccount).changeAmount(1.);
    }
}