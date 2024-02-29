package com.acme.banking.dbo.test;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.exception.client.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    private final int clientId = 1;
    private final String clientName = "ClientName";
    private final int accountId = 2;
    private final double accountAmount = 1.0;

    @Test
    public void shouldReturnIdWhenGetId() {
        Client sut = new Client(clientId, clientName);

        assertEquals(clientId, sut.getId());
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        Client sut = new Client(clientId, clientName);

        assertEquals(clientName, sut.getName());
    }

    @Test
    public void shouldReturnAccountsWhenGetAccounts() {
        Client sut = new Client(clientId, clientName);
        Account account = new SavingAccount(accountId, sut, accountAmount);

        sut.setAccount(account);

        assertTrue(sut.getAccounts().contains(account));
    }

    @Test
    public void shouldNotCreateClientWhenNullName() {
        assertThrows(ClientNameNullException.class, () -> new Client(clientId, null));
    }

    @Test
    public void shouldNotCreateClientWhenEmptyName() {
        assertThrows(IllegalClientNameArgumentException.class, () -> new Client(clientId, ""));
    }

    @Test
    public void shouldNotCreateClientWhenNegativeId() {
        assertThrows(IllegalClientIdArgumentException.class, () -> new Client(-1, clientName));
    }

    @Test
    public void shouldNotSetAccountsWhenNullAccounts() {
        Client sut = new Client(clientId, clientName);

        assertThrows(ClientAccountsNullException.class, () -> sut.setAccount(null));
    }

    @Test
    public void shouldNotCreateClientWhenAccountHasAnotherOwner() {
        Account account = new SavingAccount(accountId, new Client(clientId, clientName), accountAmount);
        Client sut = new Client(clientId, clientName);

        assertThrows(IllegalClientStateException.class, () -> sut.setAccount(account));
    }
}
