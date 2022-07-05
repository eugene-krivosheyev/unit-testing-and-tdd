package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
    @Test
    public void shouldNotCreateWhenZeroId() {
        final int id = 0;
        final Client dummyClient = new Client(1, "Name");
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
        final Client dummyClient = new Client(1, "Name");
        double amount = 0;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(dummyId, dummyClient, amount));
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int dummyId = 1;
        final Client dummyClient = new Client(1, "Name");
        double dummyAmount = 1;
        SavingAccount sut = new SavingAccount(dummyId, dummyClient, dummyAmount);
        assertAll("Client store its properties",
                () -> assertEquals(dummyId, sut.getId()),
                () -> assertEquals(dummyClient, sut.getClient()),
                () -> assertEquals(dummyAmount, sut.getAmount())
        );
    }
}