package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepo;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
    public void shouldThrowExceptionWhenNameIsNull(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("incorrect name");
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(repoStub, cashStub);
        sut.createClient(null);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("incorrect name");
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(repoStub, cashStub);
        sut.createClient("");
    }

    @Test
    public void shouldCallRepoCreateClientWhenCreateClient(){
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = spy(new Processing(repoStub, cashStub));
        sut.createClient("Aaaaaaaaaaaaaaaaaa");

        verify(sut).createClient(any());
    }

    @Test
    public void shouldThrowExceptionWhenClientIdIsNull(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("client is null");
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(repoStub, cashStub);
        sut.getAccountsByClientId(null);
    }

    @Test
    public void shouldFindAccountsWhenGetAccounts(){
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = new Processing(repoStub, cashStub);
        UUID dummy = UUID.randomUUID();
        sut.getAccountsByClientId(dummy);

        verify(repoStub).findAccountsByUUID(dummy);
    }

    @Test
    public void shouldSaveAccountsWithCorrectAmountWhenTransfer(){
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = spy(new Processing(repoStub, cashStub));
        UUID from = UUID.randomUUID();
        UUID to = UUID.randomUUID();
        SavingAccount fromA = new SavingAccount(UUID.randomUUID(), 150);
        SavingAccount toA = new SavingAccount(UUID.randomUUID(), 50);
        when(repoStub.findAccountById(from)).thenReturn(fromA);
        when(repoStub.findAccountById(to)).thenReturn(toA);
        sut.transfer(100,from,to);
        AtomicReference<SavingAccount> holder1 = new AtomicReference<>();
        AtomicReference<SavingAccount> holder2 = new AtomicReference<>();
        doAnswer(invocationOnMock -> {
            holder1.set((SavingAccount) invocationOnMock.getArgumentAt(0, List.class).get(0));
            holder2.set((SavingAccount) invocationOnMock.getArgumentAt(0, List.class).get(1));
            return Collections.singletonList(invocationOnMock.getArgumentAt(0, List.class));
        }).when(repoStub).saveAccounts(Arrays.asList(fromA,toA));

        verify(repoStub).findAccountById(from);
        verify(repoStub).findAccountById(to);
        verify(repoStub).saveAccounts(eq(Arrays.asList(fromA,toA)));
        assertEquals(50,fromA.getAmount(),1);
        assertEquals(150,toA.getAmount(),1);
    }

    @Test
    public void shouldThrowExceptionWhenTransferWithIncorrectAmount(){
        exception.expect(IllegalStateException.class);
        exception.expectMessage("invalid amount");
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = spy(new Processing(repoStub, cashStub));
        UUID from = UUID.randomUUID();
        UUID to = UUID.randomUUID();
        SavingAccount fromA = new SavingAccount(UUID.randomUUID(), 50);
        SavingAccount toA = new SavingAccount(UUID.randomUUID(), 150);
        when(repoStub.findAccountById(from)).thenReturn(fromA);
        when(repoStub.findAccountById(to)).thenReturn(toA);
        sut.transfer(100,from,to);
    }

    @Test
    public void shouldLogCashWhenCash(){
        AccountRepo repoStub = mock(AccountRepo.class);
        Cash cashStub = mock(Cash.class);
        Processing sut = spy(new Processing(repoStub, cashStub));
        UUID dummy = UUID.randomUUID();
        SavingAccount dummyA = new SavingAccount(UUID.randomUUID(), 150);
        when(repoStub.findAccountById(dummy)).thenReturn(dummyA);
        AtomicReference<SavingAccount> holder = new AtomicReference<>();
        doAnswer(invocationOnMock -> {
            holder.set((SavingAccount) invocationOnMock.getArgumentAt(0, List.class).get(0));
            return Collections.singletonList(invocationOnMock.getArgumentAt(0, List.class));
        }).when(repoStub).saveAccounts(any());
        sut.cash(100,dummy);

        verify(repoStub).saveAccounts(eq(Arrays.asList(dummyA)));
        verify(cashStub).log(100,dummy);
        assertEquals(50d,holder.get().getAmount(),1);
    }
}