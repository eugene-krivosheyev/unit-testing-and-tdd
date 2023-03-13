package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test suite")
class SavingAccountTest {
    @Test
    void shouldSaveClient() {
        SavingAccount saved = new SavingAccount(1, getClient(1, "name"), 2);
        assertEquals(1, saved.getId());
        assertNotNull(saved.getClient());
        assertEquals(2, saved.getAmount());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIdLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, getClient(1, "name"), 100));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenClientIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, 100));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenAmountLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, getClient(1, "name"), -1));
    }

    private Client getClient(int id, String name) {
        return new Client(id, name);
    }
}