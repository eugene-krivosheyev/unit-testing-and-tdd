package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SavingAccountTest {
    public static final UUID CLIENT_UUID = UUID.fromString("75852e48-dd5e-4062-921d-bae2bd91a045");
    public static final UUID ACCOUNT_UUID = UUID.fromString("c4bf82eb-b982-47bb-9df6-76a6ebc7b707");
    public static final String DUMMY_NAME = "dummy name";
    final Client client = new Client(CLIENT_UUID, DUMMY_NAME);
    final double amount = 1;

    @Test
    public void shouldSaveAccountWhenGettingNotNullClientWithPositiveAmount() {
        SavingAccount sut = new SavingAccount(ACCOUNT_UUID, client, amount);

        assertNotNull(sut.getClient());
        assertEquals(client, sut.getClient());
        assertEquals(CLIENT_UUID, sut.getClientId());
        assertEquals(ACCOUNT_UUID, sut.getId());
        assertEquals(amount, sut.getAmount(), 0.001);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldNotCreateSaveAccountWhenIdIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("UUID can`t be null");

        SavingAccount sut = new SavingAccount(null, client, amount);
    }

    @Test
    public void shouldNotCreateSaveAccountWhenClientIsNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Client can`t be null");

        SavingAccount sut = new SavingAccount(ACCOUNT_UUID, null, amount);
    }

    @Test
    public void shouldNotCreateSaveAccountWhenAmountIsNegative() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Amount can`t be negative");

        SavingAccount sut = new SavingAccount(ACCOUNT_UUID, client, -1);
    }
}