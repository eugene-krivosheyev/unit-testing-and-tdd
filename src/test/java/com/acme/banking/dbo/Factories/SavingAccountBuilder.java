package com.acme.banking.dbo.Factories;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.mock;

public class SavingAccountBuilder {
    public static final UUID ID_STUB = UUID.fromString("8fe9595d-de6e-4d07-bc56-dacdad16f5c2");

    private UUID id = ID_STUB;
    private Client client = mock(Client.class);
    private double amount = 1;

    public static SavingAccountBuilder create()
    {
        return new SavingAccountBuilder();
    }

    public SavingAccountBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public SavingAccountBuilder setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public SavingAccountBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    public SavingAccount build() {
        return new SavingAccount(id, client, amount);
    }
}
