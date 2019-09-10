package com.acme.banking.dbo.domain;

import java.util.Objects;
import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        this.id = Objects.requireNonNull(id);
        this.client = Objects.requireNonNull(client);
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getClientId() {
        return client.getId();
    }
}
