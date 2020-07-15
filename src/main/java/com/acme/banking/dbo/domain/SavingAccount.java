package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private long id;
    private Client client;
    private double amount;

    public SavingAccount(Long id, Client client, Double amount) {
        if (id == null || id < 0) throw new IllegalArgumentException("Incorrect id!");
        if (client == null) throw new IllegalArgumentException("Incorrect client!");
        if (amount == null || amount <= 0) throw new IllegalArgumentException("Incorrect amount!");

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
    public long getId() {
        return id;
    }

    @Override
    public long getClientId() {
        return client.getId();
    }
}
