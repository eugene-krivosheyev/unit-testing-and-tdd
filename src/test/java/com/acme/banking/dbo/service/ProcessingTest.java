package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountDao;
import com.acme.banking.dbo.dao.ClientDao;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


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
        SavingAccount account = new SavingAccount(1, new Client(CLIENT_ID, CLIENT_NAME), 100);
        doReturn(account).when(accountDao).selectById(CLIENT_ID);

        processing.getAccountsByClientId(CLIENT_ID);

        verify(clientDao).selectClientById(CLIENT_ID);
    }

    @Test
    void shouldTransferAmount() {
        double amount = 100;
        Account transfer = mock(Account.class);
        Account receive = mock(Account.class);
        doReturn(transfer).when(accountDao).selectById(1);
        doReturn(receive).when(accountDao).selectById(2);

        processing.transfer(1, 2, amount);

        verify(transfer).minusCash(amount);
        verify(receive).plusCash(amount);

        verify(accountDao).updateAmount(transfer);
        verify(accountDao).updateAmount(receive);


    }
}