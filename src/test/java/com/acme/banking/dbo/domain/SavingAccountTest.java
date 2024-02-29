package com.acme.banking.dbo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SavingAccountTest {

    final int id = 1;
    final Client client = new Client(1, "Test Name");
    final double amount = 5.0;

    SavingAccount sut;

    @BeforeEach
    void createSUT(){
        sut = new SavingAccount (id, client, amount);
    }

    @Test
    public void invalidIdThrowsIllegalArgumentException(){
        final int invalidId = 0;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(invalidId, client, amount));
    }

    @Test
    public void emptyClientThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, null, amount));
    }

    @Test
    public void negativeAmountThrowsIllegalArgumentException(){
        final double negativeAmount = -3.2;
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(id, client, negativeAmount));
    }

    @Test
    public void getIdReturnsExpectedId(){
        assertEquals(id, sut.getId());
    }

    @Test
    public void getClientReturnsExpectedClient(){
        assertEquals(client, sut.getClient());
    }

    @Test
    public void getAmountReturnsExpectedAmount(){
        assertEquals(amount, sut.getAmount());
    }

}