package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SavingAccountTest {
    private final int CLIENT_ID = 0;
    private final String CLIENT_NAME = "Test";

    @Test
    void shouldNotCreateSavingAccount_invalidId() {
        int id = -1;
        Client client = new Client(CLIENT_ID, CLIENT_NAME);
        double amount = 100.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount));

        assertEquals("Id can not be less than 0", exception.getMessage());
    }

    @Test
    void shouldNotCreateSavingAccount_clientIsNull() {
        int id = 1;
        double amount = 100.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(id, null, amount));

        assertEquals("Client can not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateSavingAccount_invalidAmount() {
        int id = 1;
        Client client = new Client(CLIENT_ID, CLIENT_NAME);
        double amount = -100.0;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount));

        assertEquals("Amount can not be less than 0", exception.getMessage());
    }

    @Test
    void shouldCreateSavingAccount_FieldsValid() {
        int id = 1;
        Client client = new Client(CLIENT_ID, CLIENT_NAME);
        double amount = 100.0;

        SavingAccount sut = new SavingAccount(id, client, amount);

        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(amount, sut.getAmount()),
                () -> assertEquals(client.getId(), sut.getClient().getId()),
                () -> assertEquals(client.getName(), sut.getClient().getName())
        );
    }
}
