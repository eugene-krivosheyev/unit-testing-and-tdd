package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SavingAccountTest {
    @Test
    void accountIdShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Client(null, ""));
    }

    @Test
    void accountClientShouldNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(UUID.randomUUID(), null, 0.0));
    }

    @Test
    void savingsAccountInitialStateShouldBeZero() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(UUID.randomUUID(), null, 100));
    }

    @Test
    void savingsAccountInitialStateShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new SavingAccount(UUID.randomUUID(), null, -1));
    }

    @Test
    void validSavingAccountCreationPerforms() throws IllegalArgumentException {
        UUID accountId = UUID.randomUUID();
        Client client = new Client(UUID.randomUUID(), "Client name");

        SavingAccount sut = new SavingAccount(accountId, client, 0);

        assertThat(accountId.equals(sut.getId()));
        assertThat(client.getName().equals(sut.getClient().getName()));
        assertThat(client.getId().equals(sut.getClient().getId()));
    }
}