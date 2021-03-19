package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) throws IllegalArgumentException {
        if (id == null) throw new IllegalArgumentException("Id can not be null");
        if (client == null) throw new IllegalArgumentException("Client can not be null");
        if (amount > Double.MIN_VALUE) throw new IllegalArgumentException("Amount should be greater than min value");
        if (amount < Double.MAX_VALUE) throw new IllegalArgumentException("Amount should be lesser than max value");

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
