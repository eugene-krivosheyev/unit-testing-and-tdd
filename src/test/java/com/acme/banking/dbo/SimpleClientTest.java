package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleClientTest {
    @Test
    void shouldNotCreateClient_invalidId() {
        int id = -1;
        String name  = "Test";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Client(id, name));

        assertEquals("Id can not be less than 0", exception.getMessage());
    }

    @Test
    void shouldNotCreateClient_nameIsNull() {
        int id = 1;
        String name = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Client(id, name));

        assertEquals("Name can not be empty", exception.getMessage());
    }

    @Test
    void shouldCreateClient_FieldsValid() {
        int id = 1;
        String name  = "Test";

        Client sut = new Client(id, name);

        assertAll(
                () -> assertEquals(id, sut.getId()),
                () -> assertEquals(name, sut.getName())
        );
    }
}
