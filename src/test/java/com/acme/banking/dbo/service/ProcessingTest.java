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

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
class ProcessingTest {
    int CLIENT_ID = 1;
    String CLIENT_NAME = "testName";
    @Mock
    ClientDao clientDao;
    @Mock
    AccountDao accountDao;
    @Mock
    SavingAccount savingAccount;
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
        Account account = mock(Account.class);
        Client client = mock(Client.class);
        doReturn(client).when(clientDao).selectClientById(CLIENT_ID);
        doReturn(List.of(account)).when(client).getAccounts();

        processing.getAccountsByClientId(CLIENT_ID);

        verify(clientDao).selectClientById(CLIENT_ID);
        verify(client).getAccounts();
    }

    @Test
    void shouldVerifyUpdateAmountWhenTransfer() {
        double amount = 100;
        Account transferAccount = mock(Account.class);
        Account receiveAccount = mock(Account.class);

        doReturn(transferAccount).when(accountDao).selectById(1);
        doReturn(receiveAccount).when(accountDao).selectById(2);

        processing.transfer(1, 2, amount);

        verify(transferAccount).minusCash(amount);
        verify(receiveAccount).plusCash(amount);

        verify(accountDao).updateAmount(transferAccount);
        verify(accountDao).updateAmount(receiveAccount);


    }
}