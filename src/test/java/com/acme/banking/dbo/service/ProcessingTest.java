package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repo.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ProcessingTest {

    @Test
    void createClient() {

        var clientRepoMock = mock(ClientRepository.class);
        when(clientRepoMock.generateId()).thenReturn(1);

        String testClientName = "Test name";
        Client testClient = new Client(1, testClientName);

        when(clientRepoMock.getClientById(1)).thenReturn(testClient);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenReturn(testClient);

        Processing sut = new Processing(clientRepoMock);

        Client createdClient = sut.createClient(testClientName);

        verify(clientRepoMock, times(2)).save(testClient);

        assertEquals(createdClient.getName(), testClient.getName());
        //assertEquals(sut.createClient(testClientName).getName(),clientRepoMock.getClientById(1).getName());


    }
}