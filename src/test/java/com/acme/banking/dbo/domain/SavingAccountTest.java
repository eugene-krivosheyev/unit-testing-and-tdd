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

    private Client client;

    @Before
    public void setUp() {
        client = new Client(UUID.randomUUID(), "Some client name");
    }

    @Test
    public void shouldNotCreatedWhenPassNullId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Id is null");
        final UUID idNull = null;
        final int amount = 100;

        new SavingAccount(idNull, client, amount);
    }

    @Test
    public void shouldNotCreatedWhenPassNullClient() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Client is null");
        final UUID id = UUID.randomUUID();
        final Client clientNull = null;
        final int amount = 100;

        new SavingAccount(id, clientNull, amount);
    }

    @Test
    public void shouldNotCreatedWhenPassNegativeAmount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Amount is negative");
        final UUID accountId = UUID.randomUUID();
        final int negativeAmount = -100;

        new SavingAccount(accountId, client, negativeAmount);
    }

    @Test
    public void shouldCreateSavingAccountWhenPassValidIdAndClientAndAmount() {
        final UUID savingAccountId = UUID.randomUUID();
        final double amount = 100;

        SavingAccount savingAccount = new SavingAccount(savingAccountId, client, amount);

        assertEquals(savingAccountId, savingAccount.getId());
        assertSame(client, savingAccount.getClient());
        assertEquals(100, savingAccount.getAmount(), 1e-5);
    }
}