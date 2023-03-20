package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountDao;
import com.acme.banking.dbo.dao.ClientDao;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ProcessingTest {
    int CLIENT_ID = 1;
    String CLIENT_NAME = "testName";
    @Mock
    ClientDao clientDao;
    @Mock
    AccountDao accountDao;
    Processing processing;

    @BeforeEach
    void init() {
        processing = new Processing(clientDao, accountDao);
    }

    @Test
    void shouldSaveClientByName() {
        Client client = new Client(CLIENT_ID, CLIENT_NAME);
        doReturn(client).when(clientDao).saveClient(CLIENT_NAME);

        processing.createClient(CLIENT_NAME);

        verify(clientDao).saveClient(CLIENT_NAME);
    }

    @Test
    void shouldReceiveAccountByClientId() {
        Client client = new Client(CLIENT_ID, CLIENT_NAME);
        doReturn(client).when(clientDao).selectClientById(CLIENT_ID);

        processing.getAccountsByClientId(CLIENT_ID);

        verify(clientDao).selectClientById(CLIENT_ID);
    }

    @Test
    void shouldTransferAmount() {
        double amount = 100;
        SavingAccount clientTransfer = new SavingAccount(1, new Client(CLIENT_ID, CLIENT_NAME), 1000);
        SavingAccount clientReceive = new SavingAccount(2, new Client(CLIENT_ID, CLIENT_NAME), 1000);
        doReturn(clientTransfer).when(accountDao).selectById(1);
        doReturn(clientReceive).when(accountDao).selectById(2);

        processing.transfer(1, 2, amount);

        verify(clientTransfer).minusCash(amount);
        verify(clientReceive).plusCash(amount);

        verify(accountDao).updateAmount(clientTransfer);
        verify(accountDao).updateAmount(clientReceive);


    }
}