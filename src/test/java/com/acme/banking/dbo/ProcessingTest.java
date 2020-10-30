package com.acme.banking.dbo;

import com.acme.banking.dbo.Factories.ClientBuilder;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldSaveAccountsWithChangedAmount() {
        Account fromAccountMock = mock(Account.class);
        Account toAccountMock = mock(Account.class);

        AccountRepository accountRepoMock = mock(AccountRepository.class);
        when(accountRepoMock.findById(any(UUID.class)))
                .thenReturn(fromAccountMock)
                .thenReturn(toAccountMock);

        final Processing sut = new Processing(accountRepoMock);

        sut.transfer(1, null, null);

        verify(fromAccountMock).getAmount();
        verify(fromAccountMock).setAmount(anyDouble());
        verify(toAccountMock).getAmount();
        verify(toAccountMock).setAmount(anyDouble());

        verify(accountRepoMock).save(fromAccountMock);
        verify(accountRepoMock).save(toAccountMock);
    }


}
