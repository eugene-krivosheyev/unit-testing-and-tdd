package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ProcessingTest {
    private AccountRepository accountRepoStub = mock(AccountRepository.class);
    private Cash cashmachine = mock(Cash.class);

    @Test
    public void shouldGetStoredAccountWhenGetExistingById() {
        Account accountStub = mock(Account.class);
        //Array.asList(
        //        new SavingAccount(2, new Client(), ), // CANT USE REAL OBJECTS HERE - IT'S WILL BE AN INTERGATION TEST
        //); // Array.asList - Java 8
        when(accountRepoStub.getAccountsByClientId(1))
                .thenReturn(Arrays.asList(accountStub));

        // only for id=1
        //when(accountRepoStub.getAccountsByClientId(anyInt())).thenReturn(Arrays.asList(accountStub));
        // for Mockito parameter matchers - any int
        // but this makes test less sensitive

        // Other variant - don't simulate the state of the mock, we just emulate it for the test cases scenario
        when(accountRepoStub.getAccountsByClientId(anyInt()))
                .thenReturn(Arrays.asList(accountStub))
                .thenReturn(Collections.EMPTY_SET)
                .thenThrow(IllegalStateException.class);


        final Processing sut = new Processing(accountRepoStub, cashmachine);
        //final int clientId = 1;

        assertThat(sut.getAccountsByClientId(1)).containsExactly(accountStub);
    }


    @Test
    public void shouldGetErrorAccountWhenGetNotExistingById() {
        when(accountRepoStub.getAccountsByClientId(2)).thenThrow(new IllegalStateException("!!"));
        // Usually Entity not found Exception

        final Processing sut = new Processing(accountRepoStub, cashmachine);

        assertThrows(
                IllegalStateException.class,
                () -> sut.getAccountsByClientId(2)
        );

    }

    @Test
    public void shouldTransferWhenValidAmount() {
        Account accountFromStub = mock(Account.class);
        Account accountToStub = mock(Account.class);
        when(accountFromStub.getAmount()).thenReturn(10.);
        when(accountToStub.getAmount()).thenReturn(2.);
        when(accountRepoStub.getAccountById(1)).thenReturn(accountFromStub);
        when(accountRepoStub.getAccountById(2)).thenReturn(accountToStub);
        Processing sut = new Processing(accountRepoStub, cashmachine);

        sut.transfer(1, 2, 9);

        verify(accountRepoStub, times(1)).getAccountById(1);
        verify(accountRepoStub, atLeastOnce()).getAccountById(2); // or AnyInt()

        //verify(accountRepoStub).getAccountById(1);
        verify(accountFromStub).setAmount(1);
        verify(accountToStub).setAmount(11);
        verify(accountRepoStub).save(accountFromStub);
        verify(accountRepoStub).save(accountToStub);
    }

    @Test
    public void shouldLogWhenCashed() {
        int fromAccountId = 5;
        int amount = 5;
        Processing sut = new Processing(accountRepoStub, cashmachine);

        sut.cash(amount, fromAccountId);

        verify(cashmachine).log(amount, fromAccountId);

    }
}