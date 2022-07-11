package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitSavingAccountTest {
    int accountId;
    double accountAmount;
    Client client;

    private void setAccountIdAndAmount(int id, double amount) {
        accountId = id;
        accountAmount = amount;
    }

    @BeforeEach
    private void buildDummyClient() {
        client = new Client(1, "dummy");
    }

    @AfterEach
    private void resetAccountIdAndAmount() {
        accountId = 0;
        accountAmount = 0.0;
    }


    @Test
    void shouldSaveAccountWhenInputDataIsCorrect() {
        setAccountIdAndAmount(1, 1000.0);

        SavingAccount sut = new SavingAccount(accountId, client, accountAmount);

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
        setAccountIdAndAmount(0, 1000.0);

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, accountAmount)
        );

        assertAll("Saving account fail: clientId is zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Id shouldn't be zero!", thrown.getMessage())
        );
    }

    @Test
    public void shouldNotSaveAcoountWhenClientIsNull() {
        setAccountIdAndAmount(1, 1000.0);
        client = null;

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, accountAmount)
        );

        assertAll("Saving account fail: client is null",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Client shouldn't be null!", thrown.getMessage())
        );
    }

    @Test
    public void shouldNotSaveAcoountWhenAmountIsLessThanZero() {
        setAccountIdAndAmount(1, -1000.0);

        Throwable thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(accountId, client, accountAmount)
        );

        assertAll("Saving account fail: amount is less than zero",
                () -> assertNotNull(thrown.getMessage()),
                () -> assertEquals("Amount shouldn't be less than zero!", thrown.getMessage())
        );
    }
}