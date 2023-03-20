package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {

    @Mock
    private ClientRepository clients;
    @Mock
    private AccountRepository accounts;
    @Mock
    private Client client;
    @Mock
    private Account fromAccount, toAccount, account;
    @Mock
    private Cash cash;
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

    @Test
    void shouldCreateClientWhenClientNotFound() {
        when(clients.findById(1)).thenReturn(null);
        when(clients.save(any())).thenReturn(client);

        assertThat(sut.createClient(1, "name"))
                .isNotNull()
                .isSameAs(client);

        verify(clients).findById(1);
        verify(clients).save(any());
    }

    @Test
    void shouldNotCreateClientWhenClientAlreadyExists() {
        when(clients.findById(1)).thenReturn(client);

        assertThatThrownBy(() -> sut.createClient(1, "dummyName"))
                .isInstanceOf(IllegalArgumentException.class);

        verify(clients).findById(1);
        verify(clients, never()).save(any());
    }

    @Test
    void shouldTransferWhenAtLeastOneClientWithAccount() {
        double dummyAmount = 100.0;
        when(accounts.findById(1)).thenReturn(fromAccount);
        when(accounts.findById(2)).thenReturn(toAccount);

        sut.transfer(1, 2, dummyAmount);

        verify(accounts).findById(1);
        verify(accounts).findById(2);
        verify(fromAccount).withdraw(dummyAmount);
        verify(toAccount).deposit(dummyAmount);

        verify(accounts).save(fromAccount);
        verify(accounts).save(toAccount);
    }

    @Test
    void shouldNotTransferWhenAccountFromNotFound() {
        double dummyAmount = 100.0;
        when(accounts.findById(1)).thenReturn(null);

        assertThatThrownBy(() -> sut.transfer(1, 2, dummyAmount));
    }

    @Test
    void shouldNotTransferWhenAccountToNotFound() {
        double dummyAmount = 100.0;
        when(accounts.findById(1)).thenReturn(fromAccount);
        when(accounts.findById(2)).thenReturn(null);

        assertThatThrownBy(() -> sut.transfer(1, 2, dummyAmount));
    }

    @Test
    void shouldLogCash() {
        sut.cash(1.0, 1);

        verify(cash).log(1.0, 1);
    }
}