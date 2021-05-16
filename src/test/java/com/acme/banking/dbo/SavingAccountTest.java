package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {
    private final int clientId = 1;
    private final double amount = 10.0;
    final String dummy = "client name";
    private final Client dummyClient = new Client(clientId, dummy);

    @Test
    public void shouldNotSaveAccountWhenIdIsNegative() {
        final int negativeId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(negativeId, dummyClient, amount));
    }

    @Test
    public void shouldNotSaveAccountWhenAmountIsNegative() {
        final double negativeAmount = -10.0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(clientId, dummyClient, negativeAmount));
    }

    @Test
    public void shouldNotSaveAccountWhenClientIsNull() {
        final Client nullClient = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(clientId, nullClient, amount));
    }

}
