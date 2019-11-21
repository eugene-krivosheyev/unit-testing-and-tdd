package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.dal.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Test
    public void shouldCreateClientWhenClientDataValid() {
        UUID clientId = UUID.randomUUID();
        ClientRepository clientsStub = mock(ClientRepository.class);
        when(clientsStub.create(any(String.class))).thenReturn(clientId);
        AccountRepository accounts = null;
        final Processing sut = new Processing(clientsStub, accounts);

        final UUID createdClientId = sut.createClient("Client stub");

        assertThat(createdClientId)
                .isNotNull()
                .isEqualTo(clientId);
    }

    @Test
    public void shouldTransferWhenAmountAvailable() {
        final UUID account1Id = UUID.randomUUID();
        final UUID account2Id = UUID.randomUUID();
        final AccountRepository accounts = mock(AccountRepository.class);
        Account account1 = mock(Account.class);
        when(account1.getAmount()).thenReturn(100.);
        Account account2 = mock(Account.class);
        when(account2.getAmount()).thenReturn(0.);
        when(accounts.findById(account1Id)).thenReturn(account1);
        when(accounts.findById(account2Id)).thenReturn(account2);
        final ClientRepository clients = mock(ClientRepository.class);


        new MockitoClientRepositoryBuilder()
                .withClient(new MockitoClientBuilder().withId().withName())
                .withClient(1)
                    .withAccount(2)
                .build()
            .build();

        ClientRepository clientsStub = new DbClientRepositoryBuilder()
                .withClient(new DbClientBuilder().withId().withName())
                .withClient(1, "name", 100.)
                    .withAccount(1)
                    .withAccount(2)
                .build()
            .build();

        final Processing sut = new Processing(clients, accounts);

        sut.transfer(100., account1Id, account2Id);

        verify(account1).setAmount(0.);
        verify(account2).setAmount(100.);
    }
}
