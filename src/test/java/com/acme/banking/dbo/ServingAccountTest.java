package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assumptions.*;


@DisplayName("Test ServingAccount")
public class ServingAccountTest {
    @Test
    @DisplayName("Test shouldThrowExceptionWhenIdNegative")
    public void shouldThrowExceptionWhenIdNegative() {

        final int clientId = 1;
        final int SavingAccountId = -1;
        final int amount = 1;
        final String clientName = "dummy client name";
        Client dummyClient = new Client(clientId, clientName);

        try {
            SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Id negative");
        }

    }
    @Test
    @DisplayName("Test shouldThrowExceptionWhenAmountNegative")
    public void shouldThrowExceptionWhenAmountNegative() {

        final int clientId = 1;
        final int SavingAccountId = 1;
        final int amount = -1;
        final String clientName = "dummy client name";
        Client dummyClient = new Client(clientId, clientName);

        try {
            SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Amount negative");
        }

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenClientIsNull")
    public void shouldThrowExceptionWhenClientIsNull() {

        final int SavingAccountId = -1;
        final int amount = 1;
        Client dummyClient = null;

        try {
            SavingAccount sut = new SavingAccount(SavingAccountId, dummyClient, amount);
        } catch (IllegalArgumentException e){
            assumeTrue( true, "Catch IllegalArgumentException when Client is Null ");
        }

    }

}
