package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assumptions.*;


@DisplayName("Test ServingAccount")
public class ServingAccountTest {
    @Test
    @DisplayName("Test shouldThrowExceptionWhenIdNegative")
    public void shouldThrowExceptionWhenIdNegative() {

        final int SavingAccountId = -1;
        final int amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));

    }
    @Test
    @DisplayName("Test shouldThrowExceptionWhenAmountNegative")
    public void shouldThrowExceptionWhenAmountNegative() {

        final int SavingAccountId = 1;
        final int amount = -1;
        Client dummyClient = new Client(1, "Dummy");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenClientIsNull")
    public void shouldThrowExceptionWhenClientIsNull() {

        final int SavingAccountId = -1;
        final int amount = 1;
        Client dummyClient = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));

    }

    @Test
    @DisplayName("Test shouldThrowExceptionWhenAccountNotLinkedToClient")
    public void shouldThrowExceptionWhenAccountNotLinkedToClient() {

        final int SavingAccountId = 1;
        final int amount = 1;
        Client dummyClient = new Client(1,"Dummy");

        Assertions.assertThrows(IllegalStateException.class, () -> new SavingAccount(SavingAccountId, dummyClient, amount));
    }


}
