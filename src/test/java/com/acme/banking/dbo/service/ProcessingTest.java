package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.exceptions.CreateClientException;
import com.acme.banking.dbo.exceptions.GetAccountsException;
import com.acme.banking.dbo.repo.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ProcessingTest {

    int testClientId = 1;
    String testClientName = "Test Client";

    Client testClient;
    SavingAccount testAccount;

    @BeforeEach
    void prepareTestObjects(){
        testClient = new Client(testClientId, testClientName);
        testAccount = new SavingAccount(1, testClient, 500);

    }


    @Test
    void shouldReturnClientThatSavedInClientRepository() {

        ClientRepository clientRepoMock = mock(ClientRepository.class);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(testClient);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenReturn(testClient);

        Processing sut = new Processing(clientRepoMock);

        assertAll("Method createClient(String Client name) has to return notNull Client"
                + " and Client should be equal Client saved in repo",
                () -> assertNotNull(sut.createClient(testClientName)),
                () -> assertEquals(sut.createClient(testClientName), clientRepoMock.getClientById(testClientId)));
    }

    @Test
    public void shouldNotSaveClientInRepoWhenError(){

        ClientRepository clientRepoMock = mock(ClientRepository.class);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(testClient);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenThrow(new RuntimeException());

        Processing sut = new Processing(clientRepoMock);
        Exception e = assertThrows(CreateClientException.class, () -> sut.createClient(testClientName));
        assertEquals("Error during save client!", e.getMessage());
    }

    @Test
    public void shouldReturnClientAccountsForClientFromRepo(){
        Collection<Account> testAccountList = new ArrayList<>();
        testAccountList.add(testAccount);

        Client spyClient = spy(testClient);

        //чтобы тест не зависел от метода getAccounts
        doReturn(testAccountList).when(spyClient).getAccounts();

        ClientRepository clientRepoMock = mock(ClientRepository.class);
        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(spyClient);

        Processing sut = new Processing(clientRepoMock);

        assertAll("Account list for client is not empty and contains 1 test account",
                () -> assertEquals(1, sut.getAccountsByClientId(testClientId).size()),
                () -> assertEquals(testAccountList, sut.getAccountsByClientId(testClientId)));

    }

    @Test
    public void shouldNotReturnClientAccountsWhenError(){

        ClientRepository clientRepoMock = mock(ClientRepository.class);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenThrow(new RuntimeException());

        Processing sut = new Processing(clientRepoMock);
        Exception e = assertThrows(GetAccountsException.class, () -> sut.getAccountsByClientId(testClientId));
        assertEquals("Cannot get accounts for client!", e.getMessage());
    }
}