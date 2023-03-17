package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {

    @Mock
    private ClientRepository clients;
    @Mock
    private AccountRepository accounts;
    @Mock
    private Client client;
    @Mock
    private Account account;
    @InjectMocks
    private Processing sut;

    @Test
    void shouldGetClientAccountsWhenAtLeastOneClientWithAccount() {
        when(client.getAccounts()).thenReturn(Collections.singletonList(account));
        when(clients.findById(1)).thenReturn(client);

        assertThat(sut.getAccountsByClientId(1))
                .contains(account);

        verify(clients).findById(1);
        verify(client).getAccounts();
    }

    @Test
    void shouldGetErrorWhenClientNotFound() {
        when(clients.findById(anyInt())).thenReturn(null);

        assertThatThrownBy(() -> sut.getAccountsByClientId(1));

        verify(clients).findById(anyInt());
    }

    @Test
    void shouldGetErrorWhenClientHasNotAtLeastOneAccount() {
        when(client.getAccounts()).thenReturn(Collections.emptyList());
        when(clients.findById(1)).thenReturn(client);

        assertThatThrownBy(() -> sut.getAccountsByClientId(1));

        verify(clients).findById(1);
        verify(client).getAccounts();
    }
}