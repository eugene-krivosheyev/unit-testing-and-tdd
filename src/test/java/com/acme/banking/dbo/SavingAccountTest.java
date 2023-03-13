package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {
    @Test
    public void shouldThrowExceptionWhenIdNegative() {
        int id = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(id, new Client(1, "Client"), 1);
        });
    }

    @Test
    public void shouldThrowExceptionWhenClientNull() {
        Client client = null;

        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1, client, 1);
        });
    }

    @Test
    public void shouldThrowExceptionWhenAmountNegative() {
        double amount = -1.0;

        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1, null, amount);
        });
    }
}
