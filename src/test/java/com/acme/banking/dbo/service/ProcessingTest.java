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

    //Client
    int testClientId = 1;
    String testClientName = "Test Client";
    Client testClient;

    //Accounts
    int fromAccountId = 1;
    double fromBalance = 100;
    SavingAccount spyFromAccount;
    int toAccountId = 2;
    double toBalance = 75.4;
    SavingAccount spyToAccount;


    SavingAccount testAccount;
    ClientRepository clientRepoMock;
    AccountRepository accountRepoMock;

    Processing sut;

    @BeforeEach
    void prepareTestObjects(){
        testClient = new Client(testClientId, testClientName);
        testAccount = new SavingAccount(1, testClient, 500);
        clientRepoMock = mock(ClientRepository.class);

        accountRepoMock = mock(AccountRepository.class);

        spyFromAccount = spy(new SavingAccount(fromAccountId, testClient, fromBalance));
        spyToAccount = spy(new SavingAccount(toAccountId, testClient, toBalance));

        sut = new Processing(clientRepoMock, accountRepoMock);

    }


    @Test
    void shouldReturnClientThatSavedInClientRepository() {

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
        double fromBalanceBefore = fromBalance;
        double toBalanceBefore = toBalance;

        when(accountRepoMock.getAccountById(fromAccountId)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(toAccountId)).thenReturn(spyToAccount);
        doCallRealMethod().when(spyFromAccount).getAmount();
        doCallRealMethod().when(spyToAccount).getAmount();
        doCallRealMethod().when(spyFromAccount).setBalance(fromBalance - testAmount);
        doCallRealMethod().when(spyToAccount).setBalance(toBalance + testAmount);

        sut.transfer(fromAccountId, toAccountId, testAmount);

        assertAll("Money has been sent and total balance was not changed",
                () -> assertEquals(fromBalanceBefore, spyFromAccount.getAmount() + testAmount),
                () -> assertEquals(toBalanceBefore, spyToAccount.getAmount() - testAmount),
                () -> assertEquals(fromBalanceBefore + toBalanceBefore, spyFromAccount.getAmount() + spyToAccount.getAmount())
        );
    }

    @Test
    public void shouldNotTransferWhenAccountProblems(){

        when(accountRepoMock.getAccountById(fromAccountId)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(toAccountId)).thenReturn(spyToAccount);
        doThrow(new RuntimeException()).when(spyToAccount).setBalance(Mockito.anyDouble());

        assertAll("When account repo is not available or accounts are the same, transfer has to be stopped",
                () -> assertEquals("The source account is the same as the target!",
                        getErrorTransferMessage(fromAccountId, fromAccountId, 100)),
                () -> assertEquals("Fail transaction",
                        getErrorTransferMessage(fromAccountId, toAccountId, 100)));
    }

    @Test
    public void shouldNotTransferWhenAmountIsInvalid(){

        when(accountRepoMock.getAccountById(fromAccountId)).thenReturn(spyFromAccount);
        when(accountRepoMock.getAccountById(toAccountId)).thenReturn(spyToAccount);
        doReturn(fromBalance).when(spyFromAccount).getAmount();

        assertAll("Cannot transfer money, if amount > fromBalance or amount <= 0",
                () -> assertEquals("Amount -10.0 is invalid value!",
                        getErrorTransferMessage(fromAccountId, toAccountId, -10.0)),
                () -> assertEquals("Not enough money to transfer",
                        getErrorTransferMessage(fromAccountId, toAccountId, 1000)));
    }

    @Test
    public void shouldNotTransferWhenAccountRepoProblems(){
        when(accountRepoMock.getAccountById(Mockito.anyInt())).thenThrow(new RuntimeException());
        assertEquals("Account service returned error",
                getErrorTransferMessage(fromAccountId, toAccountId, 100));
    }


    private String getErrorTransferMessage(int fromAccount, int toAccount, double amount){
       return assertThrows(TransferException.class, () -> sut.transfer(fromAccount, toAccount, amount)).getMessage();
    }
}