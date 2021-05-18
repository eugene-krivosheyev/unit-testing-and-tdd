package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SavingAccountTest {

    @Test
    public void shouldStorePropertiesWhenCreated() {
        final int id = 2;
        final Client client = new Client(1, "dummy client");
        final double amount = 3.0;

        SavingAccount sa = new SavingAccount(id, client, amount);

        assertAll("SavingAccount store its properties",
                () -> assertEquals(id, sa.getId()),
                () -> assertEquals(client, sa.getClient()),
                () -> assertEquals(amount, sa.getAmount())
        );
    }

    @Test
    public void shouldNotStoreBadPropertiesWhenCreated() {

        final Client client = new Client(1, "dummy client");

        // TODO: remove duplicated code
        IllegalArgumentException e;

        e = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(-1, client, 3.0));
        assertEquals(SavingAccount.Err.BAD_ID, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, null, 3.0));
        assertEquals(SavingAccount.Err.BAD_CLIENT, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> new SavingAccount(1, client, -1));
        assertEquals(SavingAccount.Err.BAD_AMOUNT, e.getMessage());
    }
}