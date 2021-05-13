package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {

    final int defaultSavingAccountId = 1;
    final double defaultAmount = 1000;
    final int clientId = 1;
    final String clientName = "dummy client name";
    Client defaultClient = new Client(clientId, clientName);

    @Test
    public void shouldStorePropertiesWhenCreate(){
        SavingAccount sut = new SavingAccount(defaultSavingAccountId, defaultClient, defaultAmount);
        assertEquals(defaultSavingAccountId, sut.getId());
        assertEquals(defaultAmount, sut.getAmount());
        assertEquals(defaultClient, sut.getClient());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative(){
        final int negativeSavingAccountId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(negativeSavingAccountId, defaultClient, defaultAmount));
    }
    @Test
    public void shouldThrowExceptionWhenAmountIsNegative(){
        final double negativeAmount = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(defaultSavingAccountId, defaultClient, negativeAmount));
    }

    @Test
    public void shouldThrowExceptionWhenClientIsNull(){
        Client nullClient = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(defaultSavingAccountId, nullClient, defaultAmount));
    }

}