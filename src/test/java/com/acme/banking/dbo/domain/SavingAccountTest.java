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
    private double amount;

    @Before
    public void setUp() {
        client = new Client(UUID.randomUUID(), "Some client name");
        amount = 100;
    }

    @Test
    public void shouldNotCreatedWhenPassNullId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Id is null");
        final UUID idNull = null;

        new SavingAccount(idNull, client, amount);
    }

    @Test
    public void shouldNotCreatedWhenPassNullClient() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Client is null");
        final UUID id = UUID.randomUUID();
        final Client clientNull = null;

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

        SavingAccount savingAccount = new SavingAccount(savingAccountId, client, amount);

        assertEquals(savingAccountId, savingAccount.getId());
        assertSame(client, savingAccount.getClient());
        assertEquals(100, savingAccount.getAmount(), 1e-5);
    }
}