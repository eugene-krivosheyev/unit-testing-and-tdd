package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class ProcessingTests {
    @Test
    public void shouldXXXXWhenTransfer() {
        Account accountSpy = spy(new SavingAccount(null, null, 0));
        Account fromAccountMock = mock(Account.class);
        Account toAccountMock = mock(Account.class);
        AccountRepository accountRepoMock = mock(AccountRepository.class);
        when(accountRepoMock.findById(any(UUID.class)))
                .thenReturn(fromAccountMock)
                .thenReturn(toAccountMock);

        final Processing sut = new Processing(accountRepoMock);

        sut.transfer(1, null, null);

        verify(fromAccountMock).withdraw(1);
        verify(toAccountMock).deposit(1);

        verify(accountRepoMock).save(fromAccountMock);
        verify(accountRepoMock).save(toAccountMock);
    }
}
