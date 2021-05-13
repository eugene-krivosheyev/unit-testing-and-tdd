package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testShouldNotBeCreatedWhenNegativeId() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Name"));
        assertEquals(thrown.getMessage(), "ID is expected to be positive int");
    }

    @Test
    void testShouldNotBeCreatedWhenNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
        assertEquals(thrown.getMessage(), "Name is NULL");
    }

    @Test
    void testShouldNotBeCreatedWhenNameIsEmpty() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
        assertEquals(thrown.getMessage(), "Name is empty");
    }

    @Test
    void getId() {
    }

    @Test
    void getName() {
    }
}