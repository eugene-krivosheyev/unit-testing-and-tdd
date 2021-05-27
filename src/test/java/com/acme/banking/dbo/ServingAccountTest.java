package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;


@DisplayName("Test ServingAccount")
public class ServingAccountTest {
    @Test
    @DisplayName("Test shouldReturnAmountWhenCallGetAmount")
    public void shouldReturnAmountWhenCallGetAmount() {

        final int SavingAccountId = 1;
        final double amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        assertEquals(amount, sut.getAmount());
    }

    @Test
    @DisplayName("Test shouldReturnIdWhenCallGetID")
    public void shouldReturnIdWhenCallGetID() {

        final int SavingAccountId = 1;
        final double amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        assertEquals(SavingAccountId, sut.getId());
     }

    @Test
    @DisplayName("Test shouldReturnClientWhenCallGetClient")
    public void shouldReturnClientWhenCallGetClient() {

        final int SavingAccountId = 1;
        final double amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        assertEquals(dummyClient, sut.getClient());

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenIdNegative")
    public void shouldThrowExceptionWhenIdNegative() {

        final int SavingAccountId = -1;
        final double amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));

    }
    @Test
    @DisplayName("Test shouldThrowExceptionWhenAmountNegative")
    public void shouldThrowExceptionWhenAmountNegative() {

        final int SavingAccountId = 1;
        final double amount = -1;
        Client dummyClient = new Client(1, "Dummy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenClientIsNull")
    public void shouldThrowExceptionWhenClientIsNull() {

        final int SavingAccountId = 1;
        final double amount = 1;
        Client dummyClient = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));
    }


    @Test
    @DisplayName("Test shouldAddAccountWhenCreated")
    public void shouldAddAccountWhenCallAddAccount() {

        final int clientId = 1;
        final String clientName = "Client";
        final int SavingAccountId = 1;
        final double amount = 1;

        Client dummyClient = new Client(clientId, clientName);
        SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount );

        assertFalse(sut.getClient().getAccounts().isEmpty());

    }

}
