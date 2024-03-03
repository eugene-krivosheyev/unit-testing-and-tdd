package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repo.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ProcessingTest {

    @Test
    void shouldReturnClientThatSavedInClientRepository() {

        String testClientName = "Test Client";
        int testClientId = 1;
        Client testClient = new Client(testClientId, testClientName);

        ClientRepository clientRepoMock = mock(ClientRepository.class);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenReturn(testClient);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(testClient);

        Processing sut = new Processing(clientRepoMock);


        assertAll("Method createClient(String Client name) has to return notNull Client"
                + " and Client should be equal Client saved in repo",
                () -> assertNotNull(sut.createClient(testClientName)),
                () -> assertEquals(sut.createClient(testClientName), clientRepoMock.getClientById(testClientId)));
    }
}