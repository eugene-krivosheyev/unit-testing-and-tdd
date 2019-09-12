package com.acme.banking.dbo;

import com.acme.banking.dbo.builders.*;
import com.acme.banking.dbo.dao.AccountNotFoundException;
import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.dao.ClientNotFoundException;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.NotEnoughMoneyException;
import com.acme.banking.dbo.service.Processing;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    private UUID fromAccountId;
    private UUID toAccountId;
    private double defaultAmount;
    private double transferAmount;

    @Before
    public void setUp() {
        fromAccountId = UUID.randomUUID();
        toAccountId = UUID.randomUUID();
        defaultAmount = 100;
        transferAmount = 50;
    }

    @Test
    public void shouldCallAddWhenClientCreated() throws AccountNotFoundException, ClientNotFoundException {
        ClientRepository clientRepoMock = new MockitoClientRepositoryBuilder()
                .build();
        AccountRepository accountRepoDummy = new MockitoAccountRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoMock, accountRepoDummy);

        sut.createClient("123");

        verify(clientRepoMock, times(1))
                .add(any(Client.class));
    }

    @Test
    public void shouldThrowNotEnoughMoneyExceptionWhenTransferAndNotEnoughMoney() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(0)
                .build();
        Account toAccountStub = new MockitoAccountBuilder()
                .withId(toAccountId)
                .withAmount(0)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .withAccountById(toAccountId, toAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);
        thrown.expect(NotEnoughMoneyException.class);
        thrown.expectMessage("Not enough money!");

        sut.transfer(100, fromAccountId, toAccountId);
    }

    @Test
    public void shouldCallFindByIdForFromAccountIdAndToAccountIdWhenTransfer() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        Account toAccountStub = new MockitoAccountBuilder()
                .withId(toAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoMock = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .withAccountById(toAccountId, toAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoMock);

        sut.transfer(transferAmount, fromAccountId, toAccountId);


        verify(accountRepoMock).findById(fromAccountId);
        verify(accountRepoMock).findById(toAccountId);
    }

    @Test
    public void shouldUpdateAccountsWhenTransfer() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountMock = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        Account toAccountMock = new MockitoAccountBuilder()
                .withId(toAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountMock)
                .withAccountById(toAccountId, toAccountMock)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);

        sut.transfer(transferAmount, fromAccountId, toAccountId);

        verify(fromAccountMock).setAmount(defaultAmount - transferAmount);
        verify(toAccountMock).setAmount(defaultAmount + transferAmount);
    }

    @Test
    public void shouldCallUpdateTwoTimesWhenTransfer() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        Account toAccountStub = new MockitoAccountBuilder()
                .withId(toAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoMock = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .withAccountById(toAccountId, toAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoMock);

        sut.transfer(transferAmount, fromAccountId, toAccountId);

        verify(accountRepoMock, times(2)).update(any(Account.class));
    }

    @Test
    public void shouldReturnAccountsCollectionWhenGetAccountsByClientId() throws ClientNotFoundException, AccountNotFoundException {
        Account accountStub1 = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        Account accountStub2 = new MockitoAccountBuilder()
                .withId(toAccountId)
                .withAmount(defaultAmount)
                .build();
        UUID clientId = UUID.randomUUID();
        Client clientStub = new MockitoClientBuilder()
                .withId(clientId)
                .withName("123")
                .withAccount(accountStub1)
                .withAccount(accountStub2)
                .build();
        ClientRepository clientRepoStub = new MockitoClientRepositoryBuilder()
                .withClientById(clientId, clientStub)
                .build();
        AccountRepository accountRepoDummy = new MockitoAccountRepositoryBuilder()
                .build();
        final  Processing sut = new Processing(clientRepoStub, accountRepoDummy);

        Collection<Account> accounts = sut.getAccountsByClientId(clientId);

        assertThat(accounts).isEqualTo(Arrays.asList(accountStub1, accountStub2));
    }

    @Test
    public void shouldThrowNotEnoughMoneyExceptionWhenCashAndNotEnoughMoney() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        Cash cashDummy = new MockitoCashBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);
        thrown.expect(NotEnoughMoneyException.class);
        thrown.expectMessage("Not enough money!");

        sut.cash(120, fromAccountId, cashDummy);

    }

    @Test
    public void shouldUpdateAccountWhenCash() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountMock = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountMock)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        Cash cashDummy = new MockitoCashBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);

        sut.cash(transferAmount, fromAccountId, cashDummy);

        verify(fromAccountMock).setAmount(defaultAmount - transferAmount);
    }

    @Test
    public void shouldCallLogWithAmountAndIdWhenCash() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        Cash cashMock = new MockitoCashBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);

        sut.cash(transferAmount, fromAccountId, cashMock);

        verify(cashMock).log(transferAmount, fromAccountId);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenCashAndCashIsNull() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoStub = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoStub);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("cash is null");

        sut.cash(transferAmount, fromAccountId, null);
    }

    @Test
    public void shouldCallUpdateWhenCash() throws AccountNotFoundException, NotEnoughMoneyException, ClientNotFoundException {
        Account fromAccountStub = new MockitoAccountBuilder()
                .withId(fromAccountId)
                .withAmount(defaultAmount)
                .build();
        AccountRepository accountRepoMock = new MockitoAccountRepositoryBuilder()
                .withAccountById(fromAccountId, fromAccountStub)
                .build();
        ClientRepository clientRepoDummy = new MockitoClientRepositoryBuilder()
                .build();
        Cash cashDummy = new MockitoCashBuilder()
                .build();
        final Processing sut = new Processing(clientRepoDummy, accountRepoMock);

        sut.cash(transferAmount, fromAccountId, cashDummy);

        verify(accountRepoMock, times(1)).update(any(Account.class));
    }
}
