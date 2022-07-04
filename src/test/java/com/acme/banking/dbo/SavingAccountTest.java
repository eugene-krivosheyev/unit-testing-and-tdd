package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test suite for saving account")
class SavingAccountTest {

    @Test
    public void shouldCreateWithIdMoreThanZero() {
        int id = 1;
        int dummyAmount = 1;
        Client dummyClient = new Client(1, "test");

        assertDoesNotThrow(
                () -> new SavingAccount(id, dummyClient, dummyAmount)
        );
    }

    @Test
    public void shouldNotCreateWithIdEqualsZero() {
        int id = 0;
        int dummyAmount = 1;
        Client dummyClient = new Client(1, "test");

        assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, dummyClient, dummyAmount)
        );
    }

    @Test
    public void shouldNotCreateWithIdLessThanZero() {
        int id = -1;
        int dummyAmount = 1;
        Client dummyClient = new Client(1, "test");

        assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, dummyClient, dummyAmount)
        );
    }

    @Test
    public void shouldCreateWithAmountMoreThanZero() {
        int amount = 1;
        int dummyId = 1;
        Client dummyClient = new Client(1, "test");

        assertDoesNotThrow(
                () -> new SavingAccount(dummyId, dummyClient, amount)
        );
    }

    @Test
    public void shouldNotCreateWithAmountEqualsZero() {
        int amount = 0;
        int dummyId = 1;
        Client dummyClient = new Client(1, "test");

        assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, dummyClient, amount)
        );
    }

    @Test
    public void shouldNotCreateWithAmountLessThanZero() {
        int amount = -1;
        int dummyId = 1;
        Client dummyClient = new Client(1, "test");

        assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, dummyClient, amount)
        );
    }

    @Test
    public void shouldCreateWithClientIsNotNull() {
        Client client = new Client(1, "test");
        int dummyAmount = 1;
        int dummyId = 1;

        assertDoesNotThrow(
                () -> new SavingAccount(dummyId, client, dummyAmount)
        );
    }

    @Test
    public void shouldNotCreateWithClientIsNull() {
        Client client = null;
        int dummyAmount = -1;
        int dummyId = 1;

        assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, client, dummyAmount)
        );
    }

}