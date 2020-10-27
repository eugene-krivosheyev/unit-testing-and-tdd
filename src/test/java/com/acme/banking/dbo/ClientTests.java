package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ClientTests {
    private static final UUID VALID_ID = UUID.randomUUID();
    private static final String VALID_NAME = "TEST_NAME";

    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        Client sut = new Client(VALID_ID, VALID_NAME);
        assertEquals(sut.getId(), VALID_ID);
        assertEquals(sut.getName(), VALID_NAME);
    }

    @Test
    public void shouldFailWhenCreateClientWithNullId() {
        try {
            new Client(VALID_ID, VALID_NAME);
        } catch (IllegalArgumentException exception) {
            assertNotNull(exception);
        }
    }
}
