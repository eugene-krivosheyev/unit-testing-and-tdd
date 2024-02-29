package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    private int id;
    private int invalidId;
    private Client client;
    private Client invalidClient;
    private double amount;
    private double invalidAmount;
    private SavingAccount account;

    @BeforeEach
    public void setUp() {

        id = 1;
        invalidId = -1;
        amount = 12;
        invalidAmount = -1;
        int clientId = 1;
        String clientName = "Test name";

        client = new Client(clientId, clientName);
        invalidClient = null;

        account = new SavingAccount(id, client, amount);
    }

    @Test
    public void shouldCreatedWhenValidArgs() {

        assertAll(
                () -> assertEquals(id, account.getId()),
                () -> assertEquals(client, account.getClient()),
                () -> assertEquals(amount, account.getAmount())
        );
    }

    @Test
    public void shouldNotCreatedWhenInvalidArgs() {

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidId, client, amount)),
                () -> assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, invalidClient, amount)),
                () -> assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, invalidAmount))
        );
    }

}