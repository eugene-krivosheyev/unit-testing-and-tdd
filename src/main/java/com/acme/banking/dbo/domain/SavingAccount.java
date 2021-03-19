package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null ) throw new IllegalArgumentException("id is null");
        else if (id.toString().length() != 36) throw new IllegalArgumentException("Invalid UUID string");

        if (client == null ) throw new IllegalArgumentException("client is null");

        if (amount < 0) throw new IllegalArgumentException("Amount can't be negative");

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
