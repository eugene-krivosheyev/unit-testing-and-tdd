package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class MockAccountBuilderTest {

    private MockAccountBuilder accountBuilder;

    @BeforeEach
    void setUp() {
        accountBuilder = new MockAccountBuilder();
    }

    @Test
    void shouldSetId() {
        int id = 1;
        Account mock = accountBuilder
                .withId(id)
                .build();

        assertEquals(id, mock.getId());
    }

    @Test
    void shouldSetAmount() {
        double amount = 100;
        Account mock = accountBuilder
                .withAmount(amount)
                .build();

        assertEquals(amount, mock.getAmount());
    }

    @Test
    void shouldSetClient() {
        Client dummy = mock(Client.class);
        Account mock = accountBuilder
                .withClient(dummy)
                .build();

        assertEquals(dummy, mock.getClient());
    }

    @Test
    void shouldSetZeroForAmountIfNotPresent() {
        Account mock = accountBuilder.build();

        assertEquals(0, mock.getAmount());
    }

    @Test
    void shouldSetZeroForIdIfNotPresent() {
        Account mock = accountBuilder.build();

        assertEquals(0, mock.getId());
    }

    @Test
    void shouldSetDummyClientIfNotPresent() {
        Account mock = accountBuilder.build();

        assertNotNull(mock.getClient());
    }
}