package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.exceptions.CreateClientException;
import com.acme.banking.dbo.exceptions.GetAccountsException;
import com.acme.banking.dbo.exceptions.TransferException;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class ProcessingTest {

    SavingAccount spyToAccount;

    Client testClient;
    SavingAccount testAccount;

    ClientRepository clientRepoMock;
    AccountRepository accountRepoMock;

    Processing sut;

    //Client
    int testClientId = 1;
    String testClientName = "Test Client";

    //Accounts
    int fromAccountId = 1;
    SavingAccount spyFromAccount;
    int toAccountId = 2;



    @BeforeEach
    void prepareTestObjects(){
        clientRepoMock = mock(ClientRepository.class);
        accountRepoMock = mock(AccountRepository.class);

        sut = new Processing(clientRepoMock, accountRepoMock);
    }


    @Test
    void shouldReturnClientThatSavedInClientRepository() {
        testClient = new Client(testClientId, testClientName);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(testClient);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenReturn(testClient);

        assertAll("Method createClient(String Client name) has to return notNull Client"
                + " and Client should be equal Client saved in repo",
                () -> assertNotNull(sut.createClient(testClientName)),
                () -> assertEquals(sut.createClient(testClientName), clientRepoMock.getClientById(testClientId)));
    }

    @Test
    public void shouldNotSaveClientInRepoWhenError(){

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(testClient);
        when(clientRepoMock.save(Mockito.any(Client.class))).thenThrow(new RuntimeException());

        Exception e = assertThrows(CreateClientException.class, () -> sut.createClient(testClientName));
        assertEquals("Error during save client!", e.getMessage());
    }

    @Test
    public void shouldReturnClientAccountsForClientFromRepo(){
        Collection<Account> testAccountList = new ArrayList<>();

        testClient = new Client(testClientId, testClientName);
        testAccountList.add(testAccount);
        Client spyClient = spy(testClient);

        //чтобы тест не зависел от метода getAccounts
        doReturn(testAccountList).when(spyClient).getAccounts();

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenReturn(spyClient);

        assertAll("Account list for client is not empty and contains 1 test account",
                () -> assertEquals(1, sut.getAccountsByClientId(testClientId).size()),
                () -> assertEquals(testAccountList, sut.getAccountsByClientId(testClientId)));

    }

    @Test
    public void shouldNotReturnClientAccountsWhenError(){

        ClientRepository clientRepoMock = mock(ClientRepository.class);

        when(clientRepoMock.generateId()).thenReturn(testClientId);
        when(clientRepoMock.getClientById(testClientId)).thenThrow(new RuntimeException());

        Exception e = assertThrows(GetAccountsException.class, () -> sut.getAccountsByClientId(testClientId));
        assertEquals("Cannot get accounts for client!", e.getMessage());
    }

    @Test
    public void shouldTransferMoneyConsistencyBetweenDifferentAccounts(){
        double testAmount = 14.81;
        double fromBalance = 100;
        double toBalance = 75.4;

        Client fromAccountClient = new Client(1, "Test First");
        Client toAccountClient = new Client(2, "Test Second");


        Account fromAccount = new SavingAccount(1, fromAccountClient, fromBalance);
        Account toAccount = new SavingAccount(2, toAccountClient, toBalance);

        when(accountRepoMock.getAccountById(1)).thenReturn(fromAccount);
        when(accountRepoMock.getAccountById(2)).thenReturn(toAccount);

        sut.transfer(fromAccountId, toAccountId, testAmount);

        assertAll("Money has been sent and total balance was not changed",
                () -> assertEquals(fromBalance, fromAccount.getAmount() + testAmount),
                () -> assertEquals(toBalance, toAccount.getAmount() - testAmount),
                () -> assertEquals(fromBalance + toBalance, fromAccount.getAmount() + toAccount.getAmount())
        );
    }

    @Test //TODO ок ли объединить эти два ассерта в одном тесте?
    public void shouldNotTransferWhenAccountProblems(){
        double fromBalance = 100;
        double toBalance = 75.4;
        int fromAccountId = 1;

        Client fromAccountClient = new Client(1, "Test First");
        Client toAccountClient = new Client(2, "Test Second");

        spyFromAccount = spy(new SavingAccount(fromAccountId, fromAccountClient, fromBalance));

        Account toAccount = new SavingAccount(1, toAccountClient, toBalance);

        when(accountRepoMock.getAccountById(1)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(2)).thenReturn(toAccount);

        doThrow(new RuntimeException()).when(spyFromAccount).setBalance(Mockito.anyDouble());

        assertAll("When accounts are the same or account service has problems, transfer has to be stopped",
                () -> assertEquals("The source account is the same as the target!",
                        getErrorTransferMessage(fromAccountId, fromAccountId, 100)),
                () -> assertEquals("Fail transaction",
                        getErrorTransferMessage(fromAccountId, toAccountId, 100)));
    }

    @Test
    public void shouldNotTransferWhenAmountIsInvalid(){
        double fromBalance = 100;
        double toBalance = 75.4;
        Client fromAccountClient = new Client(1, "Test First");
        Client toAccountClient = new Client(2, "Test Second");

        spyFromAccount = spy(new SavingAccount(fromAccountId, fromAccountClient, fromBalance));
        spyToAccount = spy(new SavingAccount(toAccountId, toAccountClient, toBalance));

        when(accountRepoMock.getAccountById(fromAccountId)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(toAccountId)).thenReturn(spyToAccount);
        doReturn(fromBalance).when(spyFromAccount).getAmount();
        doReturn(fromBalance).when(spyToAccount).getAmount();

        assertAll("Cannot transfer money, if amount > fromBalance or amount <= 0",
                () -> assertEquals("Amount -10.0 is invalid value!",
                        getErrorTransferMessage(fromAccountId, toAccountId, -10.0)),
                () -> assertEquals("Not enough money to transfer",
                        getErrorTransferMessage(fromAccountId, toAccountId, 1000)));
    }

    @Test
    public void shouldNotTransferWhenAccountRepoProblems(){
        when(accountRepoMock.getAccountById(Mockito.anyInt())).thenThrow(new RuntimeException());
        assertEquals("Could not get account",
                getErrorTransferMessage(fromAccountId, toAccountId, 100));
    }

    @Test
    public void shouldRollbackAmountThenCannotTrancferToAccount() {
        double testAmount = 14.81;
        double fromBalance = 100;
        double toBalance = 75.4;

        Client fromAccountClient = new Client(1, "Test First");
        Client toAccountClient = new Client(2, "Test Second");

        spyFromAccount = spy(new SavingAccount(fromAccountId, fromAccountClient, fromBalance));
        spyToAccount = spy(new SavingAccount(toAccountId, toAccountClient, toBalance));

        when(accountRepoMock.getAccountById(1)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(2)).thenReturn(spyToAccount);

        doThrow(new RuntimeException()).when(spyToAccount).setBalance(toBalance + testAmount);

        double fromAccountAmountAfterTransfer = spyFromAccount.getAmount();

        assertAll("Cannot transfer money, if amount > fromBalance or amount <= 0",
                () -> assertEquals("Fail transaction",
                        getErrorTransferMessage(fromAccountId, toAccountId, 14.81)),
                () -> assertEquals(fromBalance,fromAccountAmountAfterTransfer));
    }

    private String getErrorTransferMessage(int fromAccount, int toAccount, double amount){
       return assertThrows(TransferException.class, () -> sut.transfer(fromAccount, toAccount, amount)).getMessage();
    }
}