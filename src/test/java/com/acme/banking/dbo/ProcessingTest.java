package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.AccountRepository;
import com.acme.banking.dbo.service.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        AccountRepository dummyRepo = mock(AccountRepository.class);
        Processing sut = new Processing(dummyRepo, mock(ClientRepository.class));
        sut.getAccountsByClientId(null);
    }

    @Test
    public void shouldGetAccountsByClientIdWhenClientAndAccountExist() {
        AccountRepository stubRepo = mock(AccountRepository.class);
        UUID clientId = UUID.randomUUID();
        SavingAccount testAccount = new SavingAccount(
                UUID.randomUUID(),
                new Client(clientId, "client"),
                1.0
        );

        when(stubRepo.getAccountsByClientId(clientId)).thenReturn(
            Arrays.asList(testAccount)
        );

        when(stubRepo.getAccountsByClientId(any()))
                .thenReturn(Collections.EMPTY_LIST)
                .thenReturn(null)
                .thenThrow(new RuntimeException());

        Processing sut = new Processing(stubRepo, mock(ClientRepository.class));
        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        assertThat(accounts).containsExactly(testAccount);
    }

    @Test
    public void shouldRepositoryCreateClientWhenClientNameIsCorrect() {
        ClientRepository mockClientRepository = mock(ClientRepository.class);
        Processing sut = new Processing(
                mock(AccountRepository.class),
                mockClientRepository
        );

        sut.createClient("test name");

        verify(mockClientRepository, times(1))
                .createClient("test name");
    }
}
