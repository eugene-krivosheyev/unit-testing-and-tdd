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
        final Client client = new Client(1, "Name");
        double amount = 1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldNotCreateWhenClientIsNull() {
        final int id = 1;
        final Client client = null;
        double amount = 1;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldNotCreateWhenZeroAmount() {
        final int id = 1;
        final Client client = new Client(1, "Name");
        double amount = 0;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int id = 1;
        final Client client = new Client(1, "Name");
        double amount = 1;
        SavingAccount sut = new SavingAccount(id, client, amount);
        assertAll("Client store its properties",
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(client, sut.getClient()),
                () -> assertEquals(amount, sut.getAmount())
        );
    }
}