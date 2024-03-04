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

    @Test
    public void shouldSaveClient() {
        var stubClientRepository = mock(ClientRepository.class);
        var dummyAccountRepository = mock(AccountRepository.class);
        var expected = new Client(clientId, clientName);
        when(stubClientRepository.nextId()).thenReturn(clientId);
        when(stubClientRepository.save(expected)).thenReturn(expected);
        var sut = new Processing(stubClientRepository, dummyAccountRepository);

        var actual = sut.createClient(clientName);

        assertAll(
                () -> verify(stubClientRepository).save(expected),
                () -> assertEquals(expected, actual)
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

        assertAll(
                () -> verify(stubClientRepository).getById(client.getId()),
                () -> assertIterableEquals(List.of(expected), actual)
        );
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
        var sut = new Processing(dummyClientRepository, stubAccountRepository);
        when(stubAccountRepository.getById(fromAcc.getId())).thenReturn(fromAcc);
        when(stubAccountRepository.getById(toAcc.getId())).thenReturn(toAcc);
        when(stubAccountRepository.save(fromAcc)).thenReturn(fromAcc);
        when(stubAccountRepository.save(toAcc)).thenReturn(toAcc);

        sut.transfer(fromAcc.getId(), toAcc.getId(), transferAmount);

        assertAll(
                () -> verify(stubAccountRepository).getById(fromAccId),
                () -> verify(stubAccountRepository).getById(toAccId),
                () -> verify(stubAccountRepository).save(fromAcc),
                () -> verify(stubAccountRepository).save(toAcc),
                () -> assertEquals(fromAcc.getAmount(), fromAccBalance - transferAmount),
                () -> assertEquals(toAcc.getAmount(), toAccBalance + transferAmount)
        );
    }
}
