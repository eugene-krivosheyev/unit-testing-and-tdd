package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) { throw new IllegalArgumentException("Account id should be positive!"); }
        if (client == null) { throw new IllegalArgumentException("Account client should be not null!"); }
        if (amount < 0) { throw new IllegalArgumentException("Account amount should be positive!"); }

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
