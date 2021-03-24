package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProcessingTest {
    @Test
    public void shouldReturnNewClientWhenCreateClient() {
        AccountRepository accountsStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountsStub);

        when(accountsStub.getLastId()).thenReturn(0);
        Client client = sut.createClient("name");

        assertAll("Client saved correctly",
                  () -> assertEquals(client.getId(), 1),
                  () -> assertEquals(client.getName(), "name")
        );
    }

    @Test
    public void shouldReturnAccountWhenGetAccountsByClientId() {
        AccountRepository accountsStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountsStub);
        Account accountStub = mock(Account.class);

        when(accountsStub.findById(anyInt())).thenReturn(accountStub);
        Collection<Account> collection = sut.getAccountsByClientId(10);

        assertAll("Accounts returned correctly",
                  () -> assertEquals(collection.size(), 1),
                  () -> assertTrue(collection.contains(accountStub))
        );
    }

    @Test
    public void shouldGetErrorWhenTransferNegativeAmount() {
        AccountRepository accountsStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountsStub);

        assertThrows(IllegalArgumentException.class,
                                () -> sut.transfer(0, 1, -4));
    }

    @Test
    public void shouldGetErrorWhenTransferZeroAmount() {
        AccountRepository accountsStub = mock(AccountRepository.class);
        Processing sut = new Processing(accountsStub);

        assertThrows(IllegalArgumentException.class,
                     () -> sut.transfer(0, 1, 0));
    }

    @Test
    public void shouldSetAmountsWhenTransfer() {
        AccountRepository testAccounts = mock(AccountRepository.class);
        Processing sut = new Processing(testAccounts);
        Account accountFromTest = mock(Account.class);
        Account accountToTest = mock(Account.class);

        when(testAccounts.findById(0)).thenReturn(accountFromTest);
        when(testAccounts.findById(1)).thenReturn(accountToTest);
        when(accountFromTest.getAmount()).thenReturn(10.0);
        when(accountToTest.getAmount()).thenReturn(20.0);

        sut.transfer(0, 1, 5.0);
        assertAll(
                () -> verify(accountFromTest, times(1)).setAmount(5.0),
                () -> verify(accountToTest, times(1)).setAmount(25.0),
                () -> verify(testAccounts, times(1)).save(accountFromTest),
                () -> verify(testAccounts, times(1)).save(accountToTest)
        );
    }
}