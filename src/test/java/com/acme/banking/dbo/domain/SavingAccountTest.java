package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SavingAccountTest {

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorArgumentsAreValid() {
        UUID id = UUID.randomUUID();
        double amount = 1.0d;
        Client client = new Client(UUID.randomUUID(), randomAlphabetic(32));

        SavingAccount actual = new SavingAccount(id, client, amount);

        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(client, actual.getClient());
        assertEquals(amount, actual.getAmount(), 0.01d);
    }

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorCalledWithNullId() {
        UUID id = null;
        double amount = 1.0d;
        Client client = new Client(UUID.randomUUID(), randomAlphabetic(32));

        assertThatThrownBy(() -> new SavingAccount(id, client, amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("id must not be null");
    }

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorCalledWithNullClient() {
        UUID id = UUID.randomUUID();
        double amount = 1.0d;
        Client client = null;

        assertThatThrownBy(() -> new SavingAccount(id, client, amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("client must not be null");
    }

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorCalledWithNanAmount() {
        UUID id = UUID.randomUUID();
        double amount = Double.NaN;
        Client client = new Client(UUID.randomUUID(), randomAlphabetic(32));

        assertThatThrownBy(() -> new SavingAccount(id, client, amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must not be NaN");
    }

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorCalledWithNegativeAmount() {
        UUID id = UUID.randomUUID();
        double amount = -1.0d;
        Client client = new Client(UUID.randomUUID(), randomAlphabetic(32));

        assertThatThrownBy(() -> new SavingAccount(id, client, amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must not be negative or zero");
    }

    @Test
    public void shouldCreateNewSavingAccount_when_sutConstructorCalledWithZeroAmount() {
        UUID id = UUID.randomUUID();
        double amount = 0.0d;
        Client client = new Client(UUID.randomUUID(), randomAlphabetic(32));

        assertThatThrownBy(() -> new SavingAccount(id, client, amount))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("amount must not be negative or zero");
    }
}