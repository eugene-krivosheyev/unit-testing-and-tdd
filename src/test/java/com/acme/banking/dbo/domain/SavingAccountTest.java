package com.acme.banking.dbo.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SavingAccountTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        thrown.expect(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotCreatedWhenPassNullId() {
        thrown.expectMessage("Id is null");
        final UUID idNull = null;
        final Client client = new Client(UUID.randomUUID(), "Some client name");
        final int amount = 100;

        new SavingAccount(idNull, client, amount);
    }

    @Test
    public void shouldNotCreatedWhenPassNullClient() {
        thrown.expectMessage("Client is null");
        final UUID id = UUID.randomUUID();
        final Client clientNull = null;
        final int amount = 100;

        new SavingAccount(id, clientNull, amount);
    }

    @Test
    public void shouldNotCreatedWhenPassNegativeAmount() {
        thrown.expectMessage("Amount is negative");
        final UUID accountId = UUID.randomUUID();
        final Client client = new Client(UUID.randomUUID(), "Some client name");
        final int negativeAmount = -100;

        new SavingAccount(accountId, client, negativeAmount);
    }

    @Test
    public void shouldCreateSavingAccountWhenPassValidIdAndClientAndAmount() {
        final UUID savingAccountId = UUID.randomUUID();
        final UUID clientId = UUID.randomUUID();
        final Client client = new Client(clientId, "Some client name");
        final double amount = 100;

        SavingAccount savingAccount = new SavingAccount(savingAccountId, client, amount);

        assertEquals(savingAccountId, savingAccount.getId());
        assertSame(client, savingAccount.getClient());
        assertEquals(100, savingAccount.getAmount(), 1e-5);
    }
}