package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test suite")
public class ClientTest {


    @Test
    public void shouldStorePropertiesWhenCreated() {

        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithIncorrectClient() {

        final int clientId = 0;
        final String clientName = "dummy client name";

        assertThrows(IllegalArgumentException.class,
                () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotStorePropertiesWhenCreatedWithIncorrectClientName() {

        final int clientId = 2;
        final String clientName = "";

        assertThrows(IllegalArgumentException.class,
                () -> new Client(clientId, clientName));
    }
}
