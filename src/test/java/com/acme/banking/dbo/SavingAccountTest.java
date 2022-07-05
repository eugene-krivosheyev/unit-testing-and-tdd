package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    @Test
    void shouldSaveAccountWhenInputDataIsCorrect() {
        final int id = 1;
        final Client client = new Client(1, "dummy");
        final double amount = 1000.0;

        SavingAccount sut = new SavingAccount(id, client, amount);

        assertAll("Should save account when input data is correct",
                () -> assertNotNull(sut),
                () -> assertEquals(1, sut.getId()),
                () -> assertEquals(1000.0, sut.getAmount()),
                () -> assertEquals(1, sut.getClient().getId()),
                () -> assertEquals("dummy", sut.getClient().getName())
        );
    }

    @Test
    public void shouldThrowExceptionDuringSavingWhenIdIsZero() {
        final int invalidId = 0;
        final Client dummyClient = new Client(1, "dummy");
        final double dummyAmount = 1000.0;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(invalidId, dummyClient, dummyAmount)
        );

        assertAll("Saving account fail: clientId is zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Id shouldn't be zero!", thrown.getMessage())
        );
    }

    @Test
    public void shouldThrowExceptionDuringSavingWhenClientIsNull() {
        final int dummyId = 1;
        final Client invalidClient = null;
        final double dummyAmount = 1000.0;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, invalidClient, dummyAmount)
        );

        assertAll("Saving account fail: client is null",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Client shouldn't be null!", thrown.getMessage())
        );
    }

    @Test
    public void shouldThrowExceptionDuringSavingWhenAmountIsLessThanZero() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "dummy");
        final double invalidAmount = -1000.0;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyId, dummyClient, invalidAmount)
        );

        assertAll("Saving account fail: amount is less than zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Amount shouldn't be less than zero!", thrown.getMessage())
        );
    }
}