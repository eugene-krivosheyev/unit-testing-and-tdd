package com.acme.banking.dbo.service;

import java.util.Collection;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {

    @Captor
    ArgumentCaptor<Client> clientCaptor;
    @InjectMocks
    private Processing processing;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AccountRepository accountRepository;

    @Test
    void shouldSaveWhenCreateClient() {
        String testName = "testName";
        Client clientForSaving = mock(Client.class);
        when(clientRepository.save(clientCaptor.capture())).thenReturn(clientForSaving);

        Client savedClient = processing.createClient(testName);

        verify(clientRepository).save(clientCaptor.capture());
        assertThat(savedClient)
                .isNotNull()
                .isEqualTo(clientForSaving);
        Client captureClient = clientCaptor.getValue();
        assertEquals(testName, captureClient.getName());
        verifyNoMoreInteractions(clientRepository);
        verifyNoInteractions(accountRepository);
    }

    @Test
    void shouldReturnAccountWhenGettingById() {
        int clientId = 1;
        Client dummyClient = new Client(clientId, "testName");
        Account account = new SavingAccount(1, dummyClient, 1);
        dummyClient.addAccount(account);
        when(clientRepository.getById(clientId)).thenReturn(dummyClient);

        Collection<Account> accountsByClientId = processing.getAccountsByClientId(clientId);

        assertThat(accountsByClientId).isNotEmpty()
                .isNotNull()
                .hasSize(1)
                .contains(account);
    }

    @Test
    void shouldChangeAmountWhenTransfer() {
        int fromAccountId = 1;
        int toAccountId = 2;
        double amount = 100;

        Account fromAccount = new SavingAccount(fromAccountId, new Client(1, "dummy"), 300);
        when(accountRepository.getById(fromAccountId)).thenReturn(fromAccount);
        Account toAccount = new SavingAccount(toAccountId, new Client(2, "dummy"), 300);
        when(accountRepository.getById(toAccountId)).thenReturn(toAccount);

        processing.transfer(fromAccountId, toAccountId, amount);

        assertEquals(200, fromAccount.getAmount());
        assertEquals(400, toAccount.getAmount());
    }
    @Test
    void shouldChangeAmountWhenTransferCaptor() {
        int fromAccountId = 1;
        int toAccountId = 2;
        double amount = 100;

        Account fromAccount = mock(SavingAccount.class);//new SavingAccount(fromAccountId, new Client(1, "dummy"), 300);
        when(accountRepository.getById(fromAccountId)).thenReturn(fromAccount);
        Account toAccount = mock(SavingAccount.class);//new SavingAccount(toAccountId, new Client(2, "dummy"), 300);
        when(accountRepository.getById(toAccountId)).thenReturn(toAccount);

        processing.transfer(fromAccountId, toAccountId, amount);

        verify(fromAccount).minusAmount(100);
        verify(toAccount).plusAmount(100);
//        assertEquals(200, fromAccount.getAmount());
//        assertEquals(400, toAccount.getAmount());
    }
}