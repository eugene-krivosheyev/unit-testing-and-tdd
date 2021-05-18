package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private final double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) throw new IllegalArgumentException("id < 0");
        if (client == null) throw new IllegalArgumentException("Bad client");
        if (amount < 0) throw new IllegalArgumentException("amount < 0");

        this.id = id;
        this.client = client;
        this.amount = amount;

        client.addAccount(this);
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
