package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SavingAccountTest {
    @Test
    public void shouldSaveAccountWhenGettingNotNullClientWithPositiveAmount() {
        //region given
        UUID clientId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Client client = new Client(clientId, "client");
        double amount = 10;
        //endregion

        //region when
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        //endregion

        //region then
        assertNotNull(sut.getId());
        assertNotNull(sut.getClient());
        assertNotNull(sut.getAmount());
        assertEquals(accountId, sut.getId());
        assertEquals(client, sut.getClient());
        assertEquals(amount, sut.getAmount(), 0.001);
        //endregion
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenClientIsNull() {
        UUID stubId = UUID.randomUUID();
        Client client = null;
        double amount = 10;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("client is null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        UUID clientId = UUID.randomUUID();
        UUID stubId = null;
        Client client = new Client(clientId, "name");
        double amount = 10;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("id is null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountIsNegative() {
        UUID clientId = UUID.randomUUID();
        UUID stubId = UUID.randomUUID();
        Client client = new Client(clientId, "name");
        double amount = -1;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("amount is negative");
    }
}