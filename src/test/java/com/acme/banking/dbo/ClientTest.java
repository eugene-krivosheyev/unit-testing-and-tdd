package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Client class tests")
public class ClientTest {
    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdIsNegative() {
        final int clientId = -1;
        final String clientName = "dummy client name";

        Executable sut = () -> new Client(clientId, clientName);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "Client id should be positive!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        final int clientId = 1;
        final String clientName = null;

        Executable sut = () -> new Client(clientId, clientName);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "Client name should be not null!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        final int clientId = 1;
        final String clientName = "";

        Executable sut = () -> new Client(clientId, clientName);

        assertThrows(
                IllegalArgumentException.class,
                sut,
                "Client name should be not empty!");
    }

    @Test
    public void shouldContainsAccountWhenAccountForClientWasCreated() {
        final Client dummyClient = new Client(1, "dummy");

        final Account sut = new SavingAccount(1, dummyClient, 1.0);
        assumeFalse(dummyClient.getAccounts().isEmpty());

        assertTrue(dummyClient.getAccounts().contains(sut));
    }
}
