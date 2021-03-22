package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@DisplayName("Test suite")
public class ClientTest {
    @Test
    @DisplayName("Test case")
    public void shouldStorePropertiesWhenCreated() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        //Junit5:
        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );

    }

    @Test
    public void idShuntBeZero() {
        final int clientId = 0;
        final String clientName = "dummy client name";
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void nameShuntBeNull() {
        final int clientId = 1;
        final String clientName = null;
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }
}
