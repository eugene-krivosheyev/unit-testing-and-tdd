package com.acme.banking.dbo.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void constructor_whenArgsCorrect_thenCreateNewClient() {
        Client client = new Client(1, "name");
        assertNotNull(client);
    }

    @Test
    void constructor_whenIdNotValid_thenThrowIllegalArgumentException() {

        IllegalArgumentException actualExc = assertThrows(IllegalArgumentException.class, () -> new Client(-1, "name"));
    }

    @Test
    void constructor_whenNameIsNull_thenCreateNewClient() {

        assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
    }

    @Test
    void constructor_whenNameIsEmpty_thenCreateNewClient() {

        assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
    }
}