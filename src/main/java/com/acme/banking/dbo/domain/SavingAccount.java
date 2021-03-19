package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null) throw new IllegalArgumentException("id != null ");
        if (client == null) throw new IllegalArgumentException("client != null ");
        if (amount > Double.MAX_VALUE) throw new IllegalArgumentException("amount > Double.MAX_VALUE ");
        if (amount < -Double.MAX_VALUE) throw new IllegalArgumentException("amount < -Double.MAX_VALUE ");


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
