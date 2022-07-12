package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class ProcessingTest {
    //module test // state based testing
    @Test
    public void shouldCreateClient() {
        ClientRepository dummyRepository = Mockito.mock(ClientRepository.class);
        Processing sut = new Processing(dummyRepository);

        Assertions.assertThat(sut.createClient("stub name").getId()).isNotNull().isPositive();
    }

    // integration test // interaction based testing
    @Test
    public void shouldCreateClient2() {
        ClientRepository repositoryMock = Mockito.mock(ClientRepository.class);
        Client clientStub = Mockito.mock(Client.class);

        Processing sut = new Processing(repositoryMock);
        sut.createClient("stub name");

        Mockito.verify(repositoryMock).save(clientStub);
    }

    @Test
    public void shouldGetAccountsWhenAccountsExist() {
        Account stubAccount = Mockito.mock(Account.class);
        Client stubClient = Mockito.mock(Client.class);
        Mockito.when(stubClient.getAccounts()).thenReturn(Arrays.asList(stubAccount));
        ClientRepository stubRepo = Mockito.mock(ClientRepository.class);
        Mockito.when(stubRepo.findClientById(1)).thenReturn(stubClient);

        Processing sut = new Processing(stubRepo);

        Assertions.assertThat(sut.getAccountsByClientId(1)).contains(stubAccount);
    }

    @Test
    public void shouldNotErrorWhenCash() {
        Processing dummyProcessing = new Processing(null);
        int dummyAccountId = 1;

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> dummyProcessing.cash(1, dummyAccountId));
    }
}