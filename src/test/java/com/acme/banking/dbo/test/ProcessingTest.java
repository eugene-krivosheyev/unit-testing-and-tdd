package com.acme.banking.dbo.test;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.repository.AccountRepository;
import com.acme.banking.dbo.repository.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private final int clientId = 1;
    private final String clientName = "ClientName";

    @Test
    public void shouldSaveClient() {
        var stubClientRepository = mock(ClientRepository.class);
        var dummyAccountRepository = mock(AccountRepository.class);
        var expectedClient = new Client(clientId, clientName);
        when(stubClientRepository.nextId()).thenReturn(clientId);
        when(stubClientRepository.save(expectedClient)).thenReturn(
                new Client(expectedClient.getId(), expectedClient.getName())
        );
        var sut = new Processing(stubClientRepository, dummyAccountRepository);

        var savedClient = sut.createClient(clientName);

        verify(stubClientRepository).save(expectedClient);
        assertEquals(expectedClient, savedClient);
    }

    @Test
    public void shouldTransferMoney() {
        var dummyClientRepository = mock(ClientRepository.class);
        var stubAccountRepository = mock(AccountRepository.class);
        var client = new Client(clientId, clientName);
        var fromAcc = new SavingAccount(1, client, 100.0);
        var toAcc = new SavingAccount(2, client, 100.0);
        var sut = new Processing(dummyClientRepository, stubAccountRepository);
        when(stubAccountRepository.getById(fromAcc.getId())).thenReturn(fromAcc);
        when(stubAccountRepository.getById(toAcc.getId())).thenReturn(toAcc);
        when(stubAccountRepository.save(fromAcc)).thenReturn(fromAcc);
        when(stubAccountRepository.save(toAcc)).thenReturn(toAcc);

        sut.transfer(fromAcc.getId(), toAcc.getId(), 100.0);

        assertEquals(fromAcc.getAmount(), 0);
        assertEquals(toAcc.getAmount(), 200.0);
    }

//    @Test
//    @Disabled
//    public void shouldGetAccountsByClientId() {
//        var client = new Client(clientId, clientName);
//        client.setAccount(new SavingAccount(accountId, client, accountAmount));
//        var clientRepositoryStub = mock(ClientRepository.class);
////        when(clientRepositoryStub.getById(client.getId())).thenReturn();
//
//        var sut = new Processing(clientRepositoryStub);
//
//        var clientAccounts = sut.getAccountsByClientId(client.getId());
//
//        assertEquals(client.getAccounts(), clientAccounts);
//    }
}
