package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    @Test
    public void shouldThrowIllegalStateExceptionZeroId() {
        int id = 0;
        Client client = createClient();
        double amount = 23;

        assertThrows(IllegalStateException.class, () -> new SavingAccount(id, client, amount));
    }

    @Test
    public void shouldThrowIllegalStateExceptionClientIsNull() {
        int id = 34;
        double amount = 23;

        assertThrows(IllegalStateException.class, () -> new SavingAccount(id, null, amount));
    }

    @Test
    public void shouldThrowIllegalStateExceptionZeroAmount() {
        int id = 12;
        Client client = createClient();
        double amount = 0;

        assertThrows(IllegalStateException.class, () -> new SavingAccount(id, client, amount));
    }

    private Client createClient() {
        return new Client(134, "Alexandr");
    }
}

