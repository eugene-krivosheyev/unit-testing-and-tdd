package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private final double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id<0) throw new IllegalArgumentException();
        if (client == null) throw new IllegalArgumentException();
        if (amount <0) throw new IllegalArgumentException();
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
