package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ClientTests {

    @Test
    public void shouldCreateNewClientWhenValidArguments() {
        UUID id = UUID.randomUUID();
        String name = "TestClient";

        Client sut = new Client(id, name);

        assertEquals(sut.getName(), name);
    }
}
