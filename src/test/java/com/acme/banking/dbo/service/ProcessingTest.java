package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repositories.AccountRepository;
import com.acme.banking.dbo.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcessingTest {

    Processing sut;
    Cash cashMock;
    ClientRepository clientRepositoryMock;
    AccountRepository accountRepositoryMock;

    @BeforeEach
    void setUp() {
        cashMock = mock(Cash.class);
        clientRepositoryMock = mock(ClientRepository.class);
        accountRepositoryMock = mock(AccountRepository.class);
        sut = new Processing(cashMock, clientRepositoryMock, accountRepositoryMock);
    }

    @Test
    void shouldCreateClientWithGivenName() {
        // Given
        final String dummyName = "Dummy";
        final int clientId = 1;

        final Client clientMock = mock(Client.class);
        when(clientMock.getAccounts()).thenReturn(emptyList());
        when(clientMock.getName()).thenReturn(dummyName);
        when(clientMock.getId()).thenReturn(clientId);

        when(clientRepositoryMock.saveClient(any())).thenReturn(clientMock);

        // When
        final Client client = sut.createClient(dummyName);

        // Then
        assertAll(
                () -> assertNotNull(client),
                () -> assertEquals(dummyName, client.getName()),
                () -> assertEquals(clientId, client.getId()),
                () -> assertTrue(client.getAccounts().isEmpty())
        );
    }

    @Test
    void shouldReturnAccountsCorrespondingClientById() {
        // Given
        int clientId = 1;

        Account accountDummy = mock(Account.class);
        List<Account> accountMocks = singletonList(accountDummy);
        when(clientRepositoryMock.getAccountsByClientId(clientId)).thenReturn(accountMocks);

        // When
        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        // Then
        assertAll(
                () -> assertNotNull(accounts),
                () -> assertFalse(accounts.isEmpty()),
                () -> assertTrue(accounts.contains(accountDummy))
        );
    }

    @Test
    void shouldTransferMoneyBetweenAccounts() {
        // Given
        int senderId = 1;
        double senderMoneyAmount = 90;
        Account senderMock = mock(Account.class);
        when(senderMock.getAmount()).thenReturn(senderMoneyAmount);
        when(senderMock.getId()).thenReturn(senderId);

        int receiverId = 2;
        double receiverMoneyAmount = 10;
        Account receiverMock = mock(Account.class);
        when(receiverMock.getAmount()).thenReturn(receiverMoneyAmount);
        when(receiverMock.getId()).thenReturn(receiverId);

        when(accountRepositoryMock.getAccountById(senderId)).thenReturn(senderMock);
        when(accountRepositoryMock.getAccountById(receiverId)).thenReturn(receiverMock);

        double transferMoneyAmount = 40;

        // When
        sut.transfer(senderId, receiverId, transferMoneyAmount);

        // Then
        verify(accountRepositoryMock).getAccountById(senderId);
        verify(accountRepositoryMock).save(senderMock);
        verify(accountRepositoryMock).getAccountById(receiverId);
        verify(accountRepositoryMock).save(receiverMock);
        verify(senderMock).setAmount(senderMoneyAmount - transferMoneyAmount);
        verify(receiverMock).setAmount(receiverMoneyAmount + transferMoneyAmount);
    }

    @Test
    void shouldPrintMoneyAmountAndAccountId() {
        // Given
        double amount = 10;
        int id = 1;

        // When
        sut.cash(amount, id);

        // Then
        verify(cashMock).log(amount, id);
    }
}