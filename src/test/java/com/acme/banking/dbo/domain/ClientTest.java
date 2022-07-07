package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


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

    @Test
    public void shouldAddAccountWhenValid() {
        int dummyClientId = 1;
        String dummyName = "dummy Name";
        Client dummyClient = new Client(dummyClientId, dummyName);
        assumeThat(dummyClient.getAccounts().size() == 0);

        int dummyAccountId = 1;
        double dummyAmount = 1;

        Account dummyAccount = mock(SavingAccount.class);

        assertDoesNotThrow(() -> dummyClient.addAccount(dummyAccount));

        assertThat(dummyClient.getAccounts().size()).isEqualTo(1);
        assertThat(dummyClient.getAccounts()).contains(dummyAccount);
    }

    @Test
    public void shouldNotAddAccountWhenInvalid() {
        int dummyClientId = 1;
        String dummyName = "dummy Name";
        Client dummyClient = new Client(dummyClientId, dummyName);
        assumeThat(dummyClient.getAccounts().size() == 0);

        assertThrows(IllegalArgumentException.class, () -> dummyClient.addAccount(null));

        assertThat(dummyClient.getAccounts().size()).isEqualTo(0);
    }
}