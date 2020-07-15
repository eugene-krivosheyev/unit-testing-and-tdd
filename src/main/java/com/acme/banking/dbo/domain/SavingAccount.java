package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (client == null) throw new IllegalArgumentException();
        if (id < 0) throw new IllegalArgumentException();
        if (amount < 0) throw new IllegalArgumentException();
        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getClientId() {
        return client.getId();
    }
}
