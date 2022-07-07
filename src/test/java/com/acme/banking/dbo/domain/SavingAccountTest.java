package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SavingAccountTest {

    @Test
    public void shouldNotCreateWhenIdEqualsZero() {
        final int accountId = 0;
        final double accountAmount = 0;
        Client dummyClient = mock(Client.class);

        assertThrows(IllegalArgumentException.class,() -> new SavingAccount(accountId, dummyClient, accountAmount));
    }

    @Test
    public void shouldCreate() {
        final int accountId = 1;
        final double accountAmount = 0;

        Client dummyClient = mock(Client.class);
        assertDoesNotThrow(() -> new SavingAccount(accountId, dummyClient, accountAmount));
    }

}