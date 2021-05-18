package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class SavingAccountTest {

    public static final int ACCOUNT_ID = 1;
    public static final int CLIENT_ID = 1;
    public static final double AMOUNT = 0.5;

    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        final Client client = new Client(CLIENT_ID, "dummy client name");

        SavingAccount sut = new SavingAccount(ACCOUNT_ID, client, AMOUNT);

        assumeTrue(sut != null);

        assertAll("SavingAccount store its properties",
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(CLIENT_ID, sut.getId()),
                () -> assertEquals(AMOUNT, sut.getAmount())
        );
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNegativeId() {
        final int accountId = -1;
        final Client client = new Client(CLIENT_ID, "dummy client name");

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(accountId, client, AMOUNT), "id < 0");
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenNullClient() {

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ACCOUNT_ID, null, AMOUNT), "Bad client");
    }

    @Test
    @DisplayName("Test case")
    public void shouldNotCreateWhenBadAmount() {
        final Client client = new Client(CLIENT_ID, "dummy client name");
        final double negativeAmount = -0.5;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ACCOUNT_ID, client, negativeAmount), "amount < 0");
    }

    @Test
    @DisplayName("Test case")
    public void clientShouldContainAccountWhenAccountCreated() {
        final Client client = new Client(CLIENT_ID, "dummy client name");

        SavingAccount sut = new SavingAccount(ACCOUNT_ID, client, AMOUNT);
        assertEquals(sut, client.getAccounts().get(0));
    }
}
