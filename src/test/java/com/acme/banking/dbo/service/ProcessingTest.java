package com.acme.banking.dbo.service;

import com.acme.banking.dbo.MockitoBuilder;
import com.acme.banking.dbo.dal.AccountRepo;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("incorrect name");
        Processing sut = new MockitoBuilder().createCashMock()
                .createRepoMock().buildProcessing();
        sut.createClient(null);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("incorrect name");
        Processing sut = new MockitoBuilder().createCashMock()
                .createRepoMock().buildProcessing();
        sut.createClient("");
    }

    @Test
    public void shouldCallRepoCreateClientWhenCreateClient() {

        Processing sut = new MockitoBuilder().createCashMock()
                .createRepoMock().buildProcessingSpy();
        sut.createClient("Aaaaaaaaaaaaaaaaaa");

        verify(sut).createClient(any());
    }

    @Test
    public void shouldThrowExceptionWhenClientIdIsNull() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("client is null");

        Processing sut = new MockitoBuilder().createCashMock()
                .createRepoMock().buildProcessing();
        sut.getAccountsByClientId(null);
    }

    @Test
    public void shouldFindAccountsWhenGetAccounts() {
        MockitoBuilder builder = new MockitoBuilder();

        builder.createCashMock();

        AccountRepo repoStub = builder.createRepoMock()
                .buildAccountRepoMock();

        Processing sut = builder.buildProcessing();

        UUID dummy = UUID.randomUUID();
        sut.getAccountsByClientId(dummy);

        verify(repoStub).findAccountsByUUID(dummy);
    }

    @Test
    public void shouldSaveAccountsWithCorrectAmountWhenTransfer() {
        MockitoBuilder builder = new MockitoBuilder();

        builder.createCashMock();

        UUID from = UUID.randomUUID();
        UUID to = UUID.randomUUID();
        SavingAccount fromA = new SavingAccount(UUID.randomUUID(), 150);
        SavingAccount toA = new SavingAccount(UUID.randomUUID(), 50);

        AccountRepo repoStub = builder.createRepoMock()
                .setFindAccountByIdWhenThen(from,fromA)
                .setFindAccountByIdWhenThen(to,toA)
                .buildAccountRepoMock();

        Processing sut = builder.buildProcessing();
        sut.transfer(100, from, to);

        verify(repoStub).findAccountById(from);
        verify(repoStub).findAccountById(to);
        verify(repoStub).saveAccounts(eq(Arrays.asList(fromA, toA)));
        assertEquals(50, fromA.getAmount(), 1);
        assertEquals(150, toA.getAmount(), 1);
    }

    @Test
    public void shouldThrowExceptionWhenTransferWithIncorrectAmount() {
        exception.expect(IllegalStateException.class);
        exception.expectMessage("invalid amount");

        MockitoBuilder builder = new MockitoBuilder();

        UUID from = UUID.randomUUID();
        UUID to = UUID.randomUUID();
        SavingAccount fromA = new SavingAccount(UUID.randomUUID(), 50);
        SavingAccount toA = new SavingAccount(UUID.randomUUID(), 150);

        builder.createRepoMock()
                .setFindAccountByIdWhenThen(from,fromA)
                .setFindAccountByIdWhenThen(to,toA);
        builder.createCashMock();

        Processing sut = builder.buildProcessing();

        sut.transfer(100, from, to);
    }

    @Test
    public void shouldLogCashWhenCash() {
        MockitoBuilder builder = new MockitoBuilder();

        Cash cashStub = builder.createCashMock()
                .buildCashMock();

        UUID dummy = UUID.randomUUID();
        SavingAccount dummyA = new SavingAccount(UUID.randomUUID(), 150);

        AccountRepo repoStub = builder.createRepoMock()
                .setFindAccountByIdWhenThen(dummy,dummyA)
                .buildAccountRepoMock();
        Processing sut =builder.buildProcessing();

        sut.cash(100, dummy);

        verify(repoStub).saveAccounts(eq(Collections.singletonList(dummyA)));
        verify(cashStub).log(100, dummy);
        assertEquals(50d, dummyA.getAmount(), 1);
    }
}