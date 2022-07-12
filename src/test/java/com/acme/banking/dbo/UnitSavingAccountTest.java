package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitSavingAccountTest {
    private Client client;

    @BeforeEach
    private void buildDummyClient() {
        client = new Client(1, "dummy");
    }

    @Test
    void shouldSaveAccountWhenInputDataIsCorrect() {
        int dummyAccountId = 1;
        double dummyAccountAmount = 1000.0;

        SavingAccount sut = new SavingAccount(dummyAccountId, client, dummyAccountAmount);

        assertAll("Should save account when input data is correct",
                () -> assertNotNull(sut),
                () -> assertEquals(1, sut.getId()),
                () -> assertEquals(1000.0, sut.getAmount()),
                () -> assertEquals(1, sut.getClient().getId()),
                () -> assertEquals("dummy", sut.getClient().getName())
        );
    }

    @Test
    public void shouldNotSaveAcoountWhenIdIsZero() {
        int invalidAccountId = 0;
        double dummyAccountAmount = 1000.0;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(invalidAccountId, client, dummyAccountAmount)
        );

        assertAll("Saving account fail: clientId is zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Id shouldn't be zero!", thrown.getMessage())
        );
    }

    @Test
    public void shouldNotSaveAcoountWhenClientIsNull() {
        int dummyAccountId = 1;
        double dummyAccountAmount = 1000.0;
        client = null;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, client, dummyAccountAmount)
        );

        assertAll("Saving account fail: client is null",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Client shouldn't be null!", thrown.getMessage())
        );
    }

    @Test
    public void shouldNotSaveAcoountWhenAmountIsLessThanZero() {
        int dummyAccountId = 1;
        double invalidAccountAmount = -1000.0;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(dummyAccountId, client, invalidAccountAmount)
        );

        assertAll("Saving account fail: amount is less than zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Amount shouldn't be less than zero!", thrown.getMessage())
        );
    }
}