package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Test suite Saving Account")
public class SavingAccountTest {
    public static final int ID_STUB = 1;

    @Test
    public void shouldCreatedAccountWhenCreated() {
        Client fixture = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB, fixture, 0.0);

        assertAll("SavingAccount its properties",
                () -> assertTrue(sut.getId() != 0),
                () -> assertTrue(sut.getClient() != null),
                () -> assertTrue(sut.getAmount() >= 0.0));
    }

    @Test
    public void shouldCreatedAccountWhenGetAmountPositive() {
        Client fixture = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB, fixture, 0.1);

        assertTrue(sut.getAmount() == 0.1);
    }

    @Test
    public void shouldGetErrorWhenIdZero() {
        final int id = 0;
        final int amount = 1;
        Client fixture = new Client(ID_STUB, "dummy client name");

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, fixture, amount));
    }

    @Test
    public void shouldGetErrorWhenClientNull() {
        final int amount = 1;

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, null, amount));
    }

    @Test
    public void shouldGetErrorWhenAmountNegative() {
        final int amount = -1;
        Client fixture = new Client(ID_STUB, "dummy client name");

        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(ID_STUB, fixture, amount));
    }
}
