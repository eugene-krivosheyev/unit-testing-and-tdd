package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test suite")
class SavingAccountTest {
    @Test
    void shouldSaveClient() {
        SavingAccount saved = new SavingAccount(0, getClient(1, "name"), 0d);

        assertEquals(0d, saved.getId());
        assertNotNull(saved.getClient());
        assertEquals(0d, saved.getAmount());
        assertEquals(1, saved.getClient().getId());
        assertEquals("name", saved.getClient().getName());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, getClient(1, "name"), 0d));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, null, 0d));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(0, getClient(0, "name"), -1d));
    }

    private Client getClient(int id, String name) {
        return new Client(id, name);
    }
}