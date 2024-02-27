package com.acme.banking.dbo.test;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    private int id = 1;
    private double amount = 1.2;
    private Client client = new Client(id,"client name");

    @Test
    public void shouldReturnIdWhenGetId() {

        SavingAccount sut = new SavingAccount(id, client,amount);

        assertEquals(id, sut.getId());
    }

    @Test
    public void shouldReturnAmountWhenGetAmount() {

        SavingAccount sut = new SavingAccount(id, client,amount);

        assertEquals(amount, sut.getAmount());
    }

    @Test
    public void shouldReturnClientWhenGetClient() {

        SavingAccount sut = new SavingAccount(id, client,amount);

        assertEquals(client, sut.getClient());
    }

    @Test
    public void shouldNotCreateSavingAccountWhenNegativeId() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client,amount));

        assertEquals("Id is not valid", thrown.getMessage());
    }

    @Test
    public void shouldNotCreateSavingAccountWhenNullClient() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, null,amount));

        assertEquals("Client is null", thrown.getMessage());
    }

    @Test
    public void shouldNotCreateSavingAccountWhenNegativeAmount() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client,-1));

        assertEquals("Amount can not be negative", thrown.getMessage());
    }

}

