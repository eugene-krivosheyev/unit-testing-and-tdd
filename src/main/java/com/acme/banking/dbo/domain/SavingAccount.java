package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null) throw new IllegalArgumentException("id must not be null");

        if (client == null) throw new IllegalArgumentException("client must not be null");
        if (client.getId() == id) throw new IllegalArgumentException("id must not be equal client's id");

        if (amount < 0) throw new IllegalArgumentException("amount must not be positive");

        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
