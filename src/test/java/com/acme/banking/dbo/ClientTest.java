package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Client Test")
public class ClientTest {

    @Test
    @DisplayName("Negative clientId testCase")
    public void shouldThrowExceptionWhenClientIdNegative() {
        final int clientId = -1;
        final String clientName = "dummy client name";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    @DisplayName("Null or empty clientName testCase")
    public void shouldThrowExceptionWhenClientNameIsNullOrEmpty() {
        final int clientId = 1;
        final String nullClientName = null;
        final String emptyClientName = "";

        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, nullClientName));
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, emptyClientName));
    }

    @Test
    @DisplayName("Client initialize with valid params testCase")
    public void shouldCreateClientWhenParamsAreValid() {
        final int clientId = 1;
        final String clientName = "dummy client name";

        Client sut = new Client(clientId, clientName);
        assumeTrue(sut != null);

        assertAll("Client store its properties",
                () -> assertEquals(clientId, sut.getId()),
                () -> assertEquals(clientName, sut.getName())
        );
    }
}
