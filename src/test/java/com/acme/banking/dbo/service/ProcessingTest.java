package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Client;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProcessingTest {

    @Test
    void createValidClient() {
        var clientRepositoryMock = mock(ClientRepository.class);
        var processingService = new Processing(clientRepositoryMock);
        var expectedClient = new Client(1, "Ivan");

        when(clientRepositoryMock.saveClientWithName("Ivan")).thenReturn(new Client(1, "Ivan"));
        var actualClient = processingService.createClient("Ivan");

        assertAll(
                () -> assertEquals(expectedClient.getId(), actualClient.getId()),
                () -> assertEquals(expectedClient.getName(), actualClient.getName()),
                () -> assertEquals(expectedClient.getAccounts().size(), actualClient.getAccounts().size())
        );
    }

    @Test
    void createInvalidClient() {
        var clientRepositoryMock = mock(ClientRepository.class);
        var processingService = new Processing(clientRepositoryMock);
        var expectedClient = new Client(2, "Ivan");

        when(clientRepositoryMock.saveClientWithName("Sergey")).thenReturn(new Client(1, "Ivan"));
        var actualClient = processingService.createClient("Sergey");

        assertAll(
                () -> assertEquals(expectedClient.getId(), actualClient.getId()),
                () -> assertEquals(expectedClient.getName(), actualClient.getName()),
                () -> assertEquals(expectedClient.getAccounts().size(), actualClient.getAccounts().size())
        );
    }

    @Test
    void getAccountsByClientId() {
    }

    @Test
    void transfer() {
    }

    @Test
    void cash() {
    }
}