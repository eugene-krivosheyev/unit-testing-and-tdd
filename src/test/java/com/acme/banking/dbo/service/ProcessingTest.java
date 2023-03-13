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

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {
    @Mock
    AccountRepository accountRepository;
    @Mock
    ClientRepository clientRepository;
    @InjectMocks
    Processing sut;


    @Test
    void shouldCreateClient() {
        var returnedClient = new Client(1, "dummyName");
        when(clientRepository.save(any())).thenReturn(returnedClient);

        var savedClient = sut.createClient("dummyName");

        assertEquals(returnedClient, savedClient);

        verify(clientRepository).save(any(Client.class));
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldNotCreateClientWhenClientNameWrong() {
        assertThrows(IllegalArgumentException.class, () -> sut.createClient("  "));

        verifyNoInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldGetAccountsByClientId() {
        var returnedClient = mock(Client.class);
        var returnedAccount = mock(Account.class);
        when(clientRepository.finById(anyInt())).thenReturn(returnedClient);
        when(returnedClient.getAccounts()).thenReturn(List.of(returnedAccount));

        var answer = sut.getAccountsByClientId(1);
        assertTrue(answer.contains(returnedAccount));

        verify(clientRepository).finById(1);
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldReturnEmptyListWhenGetAccountsByClientIdNotFoundClient() {
        when(clientRepository.finById(anyInt())).thenReturn(null);

        var answer = sut.getAccountsByClientId(1);

        assertTrue(answer.isEmpty());

        verify(clientRepository).finById(1);
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldReturnEmptyListWhenGetAccountsByClientIdNotFoundAccounts() {
        var returnedClient = mock(Client.class);
        when(clientRepository.finById(anyInt())).thenReturn(returnedClient);
        when(returnedClient.getAccounts()).thenReturn(null);

        var answer = sut.getAccountsByClientId(1);
        assertTrue(answer.isEmpty());

        verify(clientRepository).finById(1);
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldTransferSuccessfully() {
        var accountFrom = mock(Account.class);
        var accountTo = mock(Account.class);
        when(accountRepository.findById(1)).thenReturn(accountFrom);
        when(accountRepository.findById(2)).thenReturn(accountTo);
        when(accountFrom.getAmount()).thenReturn(100d);
        doNothing().when(accountFrom).withdraw(anyDouble());
        doNothing().when(accountTo).deposit(anyDouble());
        when(accountRepository.save(accountFrom)).thenReturn(accountFrom);
        when(accountRepository.save(accountTo)).thenReturn(accountTo);

        var transferResult = sut.transfer(1, 2, 100d);
        assertTrue(transferResult);

        verify(accountRepository).findById(1);
        verify(accountRepository).findById(2);
        verify(accountFrom).getAmount();
        verify(accountFrom).withdraw(100d);
        verify(accountTo).deposit(100d);
        verify(accountRepository).save(accountFrom);
        verify(accountRepository).save(accountTo);
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(accountFrom);
        verifyNoMoreInteractions(accountTo);
        verifyNoInteractions(clientRepository);

    }

    @Test
    void shouldNotTransferSuccessfullyWhenAccountAmountLessTransferAmount() {
        var accountFrom = mock(Account.class);
        var accountTo = mock(Account.class);
        when(accountRepository.findById(1)).thenReturn(accountFrom);
        when(accountRepository.findById(2)).thenReturn(accountTo);
        when(accountFrom.getAmount()).thenReturn(1d);

        var transferResult = sut.transfer(1, 2, 100d);
        assertFalse(transferResult);

        verify(accountRepository).findById(1);
        verify(accountRepository).findById(2);
        verify(accountFrom).getAmount();
        verifyNoMoreInteractions(accountRepository);
        verifyNoMoreInteractions(accountFrom);
        verifyNoMoreInteractions(accountTo);
        verifyNoInteractions(clientRepository);

    }

    @Test
    void shouldNotTransferWhenAccountToNotFound() {
        var accountFrom = mock(Account.class);
        when(accountRepository.findById(1)).thenReturn(accountFrom);
        when(accountRepository.findById(2)).thenReturn(null);

        var transferResult = sut.transfer(1, 2, 100d);
        assertFalse(transferResult);

        verify(accountRepository).findById(1);
        verify(accountRepository).findById(2);
        verifyNoMoreInteractions(accountRepository);
        verifyNoInteractions(accountFrom);
        verifyNoInteractions(clientRepository);
    }

    @Test
    void shouldNotTransferWhenAccountFromNotFound() {
        var accountTo = mock(Account.class);
        when(accountRepository.findById(1)).thenReturn(null);
        when(accountRepository.findById(2)).thenReturn(accountTo);

        var transferResult = sut.transfer(1, 2, 100d);
        assertFalse(transferResult);

        verify(accountRepository).findById(1);
        verify(accountRepository).findById(2);
        verifyNoMoreInteractions(accountRepository);
        verifyNoInteractions(accountTo);
        verifyNoInteractions(clientRepository);
    }
}