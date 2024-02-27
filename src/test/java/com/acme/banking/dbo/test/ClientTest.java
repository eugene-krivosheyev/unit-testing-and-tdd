package com.acme.banking.dbo.test;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    private final int id = 1;
    private final String name = "ClientName";

    @Test
    public void shouldReturnIdWhenGetId() {
        Client sut = new Client(id, name);

        assertEquals(id, sut.getId());
    }

    @Test
    public void shouldReturnNameWhenGetName() {
        Client sut = new Client(id, name);

        assertEquals(name, sut.getName());
    }

    @Test
    public void shouldNotCreateClientWithNullName() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new Client(id, null));

        assertEquals("Name is null", thrown.getMessage());
    }

    @Test
    public void shouldNotCreateClientWithEmptyName() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new Client(id, ""));
        assertEquals("Name is empty", thrown.getMessage());
    }

    @Test
    public void shouldNotCreateClientWithNegativeId() {
        var thrown = assertThrows(IllegalArgumentException.class, () -> new Client(-1, name));
        assertEquals("Id is not valid", thrown.getMessage());
    }
}
