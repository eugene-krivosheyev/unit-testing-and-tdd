package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SavingAccountTest {
    @Test
    public void shouldSaveAccountWhenGettingNotNullClientWithPositiveAmount() {
        //region Fixture | Arrange | Given
        UUID clientId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Client client = new Client(clientId, "test");
        double amount = 10;
        //endregion
        //region Assumptions check

        //endregion

        //region Act | When
        SavingAccount sut = new SavingAccount(accountId, client, amount);
        //endregion

        //region Assert | Then
        assertNotNull(sut.getClient());
        assertEquals(client, sut.getClient());
        assertEquals(clientId, sut.getClientId());
        assertEquals(accountId, sut.getId());
        assertEquals(amount, sut.getAmount(), 0.001);
        //endregion
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSaveAccountIdIsNull() {
        UUID clientId = UUID.randomUUID();
        UUID accountId = null;
        String name = "test";
        double amount = 10;
        Client client = new Client(clientId, name);
        SavingAccount sut = new SavingAccount(accountId, client, amount);

        expectedException.expectMessage("UUID can`t be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSaveAccountClientIsNull() {
        UUID accountId = UUID.randomUUID();
        String name = "test";
        double amount = 10;
        Client client = null;
        SavingAccount sut = new SavingAccount(accountId, client, amount);

        expectedException.expectMessage("Client can`t be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSaveAccountAmountIsNegative() {
        UUID clientId = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        Client client = new Client(clientId, "test");
        double amount = -1;

        SavingAccount sut = new SavingAccount(accountId, client, amount);

        expectedException.expectMessage("Amount can`t be negative");
    }
}