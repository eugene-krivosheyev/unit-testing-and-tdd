package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SavingAccountTest {

    @Test
    public void shouldCreateSavingAccountWhenNotNull() {
        Client fixture = new Client(UUID.randomUUID(), "dummy");
        SavingAccount sut = new SavingAccount(UUID.randomUUID(), fixture, 1000);

        assertNotNull(sut.getId());
        assertNotNull(sut.getClient());
        assertTrue(sut.getAmount() > 0.0);
    }
}
