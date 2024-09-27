package com.acme.banking.dbo.test;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private final int clientId = 1;
    private final String clientName = "ClientName";

    // TODO: переделать все тесты на заглушки (без создания объектов) кроме первого
    @Test
    public void shouldSaveClient() {
        var stubClientRepository = mock(ClientRepository.class);
        var stubClient = mock(Client.class);
        when(stubClientRepository.nextId()).thenReturn(clientId);
        when(stubClientRepository.save(any(Client.class))).thenReturn(stubClient);
        when(stubClient.getId()).thenReturn(clientId);
        when(stubClient.getName()).thenReturn(clientName);
        var sut = new Processing(stubClientRepository, mock(AccountRepository.class));

        var actual = sut.createClient(clientName);

        assertAll(
                () -> verify(stubClientRepository).save(any(Client.class)),
                () -> assertEquals(clientId, actual.getId()),
                () -> assertEquals(clientName, actual.getName())
        );
    }

    @Test
    public void shouldGetAccountByClientId() {
        var stubClientRepository = mock(ClientRepository.class);
        var dummyAccountRepository = mock(AccountRepository.class);
        var client = new Client(clientId, clientName);
        var expected = new SavingAccount(1, client, 100.0);
        client.setAccount(expected);
        when(stubClientRepository.getById(clientId)).thenReturn(client);
        var sut = new Processing(stubClientRepository, dummyAccountRepository);

        var actual = sut.getAccountsByClientId(client.getId());

        assertIterableEquals(List.of(expected), actual);
    }

    @Test
    public void shouldTransferMoney() {
        var dummyClientRepository = mock(ClientRepository.class);
        var stubAccountRepository = mock(AccountRepository.class);
        var client = new Client(clientId, clientName);
        var fromAccId = 1;
        var toAccId = 2;
        var fromAccBalance = 100.0;
        var toAccBalance = 100.0;
        var fromAcc = new SavingAccount(fromAccId, client, fromAccBalance);
        var toAcc = new SavingAccount(toAccId, client, toAccBalance);
        var transferAmount = 100.0;
        when(stubAccountRepository.getById(fromAcc.getId())).thenReturn(fromAcc);
        when(stubAccountRepository.getById(toAcc.getId())).thenReturn(toAcc);
        when(stubAccountRepository.save(fromAcc)).thenReturn(fromAcc);
        when(stubAccountRepository.save(toAcc)).thenReturn(toAcc);
        var sut = new Processing(dummyClientRepository, stubAccountRepository);

        sut.transfer(fromAcc.getId(), toAcc.getId(), transferAmount);

        assertAll(
                () -> verify(stubAccountRepository).save(fromAcc),
                () -> verify(stubAccountRepository).save(toAcc),
                () -> assertEquals(fromAcc.getAmount(), fromAccBalance - transferAmount),
                () -> assertEquals(toAcc.getAmount(), toAccBalance + transferAmount)
        );
    }
}
