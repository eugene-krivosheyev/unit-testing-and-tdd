package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldStorePropertiesWhenIdIsPositiveAndClientIsNonNullAndAmountIsPositive() {
        //given
        int id = 1;
        double amount = 10;
        int clientId = 2;
        String clientName = "test";
        Client client = new Client(clientId, clientName);

        //when
        SavingAccount sut = new SavingAccount(id, client, amount);

        //then
        assertEquals(id, sut.getId());
        assertEquals(client, sut.getClient());
        assertEquals(amount, sut.getAmount());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        //given
        int id = - 1;
        double amount = 10;
        int clientId = 2;
        String clientName = "test";
        Client client = new Client(clientId, clientName);

        //when

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("id should not be less than 0"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        //given
        int id = 1;
        double amount = 10;
        Client client = null;

        //when

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("client should not be null"));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountIsNagative() {
        //given
        int id = 1;
        double amount = -1;
        int clientId = 2;
        String clientName = "test";
        Client client = new Client(clientId, clientName);

        //when

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new SavingAccount(id, client, amount),
                "Expected new object but was thrown exception"
        );

        //then
        assertTrue(thrown.getMessage().contentEquals("amount should not be less than 0"));
    }
}
