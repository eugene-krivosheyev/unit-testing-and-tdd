package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class ClientTest {

    @Test
    public void shouldNotCreateWhenIdEqualsZero() {
        final int clientId = 0;
        final String clientName = "dummy client name";
        assertThrows(IllegalArgumentException.class,() -> new Client(clientId, clientName));
    }

    @Test
    public void shouldNotCreateWhenNameIsEmpty() {
        final int clientId = 1;
        final String clientName = "";
        assertThrows(IllegalArgumentException.class, () -> new Client(clientId, clientName));
    }

    @Test
    public void shouldCreate() {
        final int clientId = 1;
        final String clientName = "Name";
        assertDoesNotThrow(() -> new Client(clientId, clientName));
    }
}