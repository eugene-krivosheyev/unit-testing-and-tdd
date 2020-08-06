package com.acme.banking.dbo.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;

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
        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(accountId)),
                        hasProperty("client", equalTo(client)),
                        hasProperty("amount", equalTo(amount))
                )
        );
        assertEquals(clientId, sut.getClientId());
        //endregion
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldNotSaveWhenClientIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("client is null");
        UUID stubId = UUID.randomUUID();
        Client client = null;
        double amount = 10;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
    }

    @Test
    public void shouldNotSaveWhenIdIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("id is null");
        UUID clientId = UUID.randomUUID();
        UUID stubId = null;
        Client client = new Client(clientId, "name");
        double amount = 10;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
    }

    @Test
    public void shouldNotSaveWhenAmountIsNegative() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("amount is negative");
        UUID clientId = UUID.randomUUID();
        UUID stubId = UUID.randomUUID();
        Client client = new Client(clientId, "name");
        double amount = -1;

        SavingAccount sut = new SavingAccount(stubId, client, amount);
    }
}