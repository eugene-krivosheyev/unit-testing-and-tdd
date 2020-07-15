package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private long id;
    private Client client;
    private double amount;

    public SavingAccount(long id, Client client, double amount) {
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
