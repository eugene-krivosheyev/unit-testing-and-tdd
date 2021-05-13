package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SavingAccountTest {
    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        final int accountId = 1;
        final int clientId = 1;
        final Client client = new Client(clientId, "dummy client name");
        final double amount = 0.5;

        SavingAccount sut = new SavingAccount(accountId, client, amount);

        assumeTrue(sut != null);

        assertAll("SavingAccount store its properties",
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNegativeId() {
        final int accountId = -1;
        final int clientId = 1;
        final Client client = new Client(clientId, "dummy client name");
        final double amount = 0.5;

        try {
            SavingAccount sut = new SavingAccount(accountId, client, amount);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "id < 0");
        }
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNullClient() {
        final int accountId = 1;
        final double amount = 0.5;

        try {
            SavingAccount sut = new SavingAccount(accountId, null, amount);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "Bad client");
        }
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenBadAmount() {
        final int accountId = 1;
        final int clientId = 1;
        final Client client = new Client(clientId, "dummy client name");
        final double negativeAmount = -0.5;

        try {
            SavingAccount sut = new SavingAccount(accountId, client, negativeAmount);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
            assertEquals(e.getMessage(), "amount < 0");
        }
    }
}
