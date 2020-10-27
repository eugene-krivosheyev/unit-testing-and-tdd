package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ClientTests {
    private static final UUID DUMMY_ID = UUID.randomUUID();
    private static final String DUMMY_NAME = "DUMMY_NAME";


    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        Client sut = new Client(DUMMY_ID, DUMMY_NAME);

        assertEquals(sut.getId(), DUMMY_ID);
        assertEquals(sut.getName(), DUMMY_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateClientWithNullId() {
        new Client(null, DUMMY_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateClientWithNameIsNull() {
        new Client(DUMMY_ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenCreateClientWithEmptyName() {
        new Client(DUMMY_ID, "");
    }
}
