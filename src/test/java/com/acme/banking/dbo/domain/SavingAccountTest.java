package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SavingAccountTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionForIdWhenPassNullId() {
        Exception exception = null;
        try {
            new SavingAccount(null, new Client(UUID.randomUUID(), "Some client name"), 100);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Id is null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForClientWhenPassNullClient() {
        Exception exception = null;
        try {
            new SavingAccount(UUID.randomUUID(), null, 100);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Client is null", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForAmountWhenPassNegativeAmount() {
        Exception exception = null;
        try {
            new SavingAccount(UUID.randomUUID(), new Client(UUID.randomUUID(), "Some client name"), -100);
        } catch (IllegalArgumentException e) {
            exception = e;
        }

        assertNotNull(exception);
        assertEquals("Amount is negative", exception.getMessage());
    }

    @Test
    public void shouldCreateSavingAccountWhenPassValidIdAndClientAndAmount() {
        UUID savingAccountId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        Client client = new Client(clientId, "Some client name");
        double amount = 100;

        SavingAccount savingAccount = new SavingAccount(savingAccountId, client, amount);

        assertEquals(savingAccountId, savingAccount.getId());
        assertSame(client, savingAccount.getClient());
        assertEquals(100, savingAccount.getAmount(), 1e-5);
    }
}