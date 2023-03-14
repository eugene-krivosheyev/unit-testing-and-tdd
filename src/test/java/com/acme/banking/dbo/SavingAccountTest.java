package com.acme.banking.dbo;

import java.util.Random;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    private final Random RANDOM = new Random();

    @Test
    public void shouldStorePropertiesWhenIdIsPositiveAndClientIsNonNullAndAmountIsPositive() {
        //given
        int id = RANDOM.nextInt(2147483647);
        double amount = Math.random();
        int clientId = RANDOM.nextInt(2147483647);
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
    public void shouldStorePropertiesWhenIdIsPositiveAndClientIsNonNullAndZeroAmount() {
        //given
        int id = RANDOM.nextInt(2147483647);
        double amount = 0;
        int clientId = RANDOM.nextInt(2147483647);
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
    public void shouldStorePropertiesWhenIdZeroAndClientIsNonNullAndAmountIsPositive() {
        //given
        int id = 0;
        double amount = Math.random();
        int clientId = RANDOM.nextInt(2147483647);
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
        int id = - (RANDOM.nextInt(2147483647));
        double amount = Math.random();
        int clientId = RANDOM.nextInt(2147483647);
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
        int id = RANDOM.nextInt(2147483647);
        double amount = Math.random();
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
        int id = RANDOM.nextInt(2147483647);
        double amount = - (Math.random());
        int clientId = RANDOM.nextInt(2147483647);
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
