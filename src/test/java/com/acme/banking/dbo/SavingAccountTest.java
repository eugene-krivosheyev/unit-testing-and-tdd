package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    @Test
    public void shouldNotCreateWhenZeroId() {
        final int id = 0;
        final Client dummyClient = new Client(1, "dummy Name");
        double dummyAmount = 1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, dummyClient, dummyAmount));
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        final int dummyId = 1;
        final Client client = null;
        double dummyAmount = 1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyId, client, dummyAmount));
    }

    @Test
    public void shouldNotCreateWhenZeroAmount() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "dummy Name");
        double amount = 0;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyId, dummyClient, amount));
    }

    @Test
    public void shouldCreateWhenValidData() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "dummy Name");
        double dummyAmount = 1;
        assertDoesNotThrow(() -> new SavingAccount(dummyId, dummyClient, dummyAmount));
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "dummy Name");
        double dummyAmount = 1;
        SavingAccount sut = new SavingAccount(dummyId, dummyClient, dummyAmount);
        assertAll("SavingAccount store its properties",
                () -> assertEquals(dummyId, sut.getId()),
                () -> assertEquals(dummyClient, sut.getClient()),
                () -> assertEquals(dummyAmount, sut.getAmount())
        );
    }
}