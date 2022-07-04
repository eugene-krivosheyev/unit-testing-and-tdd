package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    public void shouldNotCreateWhenIdEqualsZero() {
        final int clientId = 1;
        final int accountId = 0;
        final double accountAmount = 0;
        final String clientName = "Name";
        Client sut = new Client(clientId, clientName);

        assertThrows(IllegalArgumentException.class,() -> new SavingAccount(accountId, sut, accountAmount));
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        final int accountId = 1;
        final double accountAmount = 0;
        Client sut = null;

        assertThrows(IllegalArgumentException.class,() -> new SavingAccount(accountId, sut, accountAmount));
    }

    @Test
    public void shouldCreate() {
        final int clientId = 1;
        final int accountId = 1;
        final double accountAmount = 0;
        final String clientName = "Name";
        Client sut = new Client(clientId, clientName);
        assertDoesNotThrow(() -> new SavingAccount(accountId, sut, accountAmount));
    }

}