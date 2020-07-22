package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private long id;
    private Client client;
    private double amount;

    public SavingAccount(long id, Client client, double amount) {
        if (id < 0) throw new IllegalArgumentException("id is less than zero");
        if (client == null) throw new IllegalArgumentException("client in null");
        if (amount < 0.0) throw new IllegalArgumentException("amount is less than zero");

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

    @Override
    public void withdraw(double amount) {
        this.amount -= amount;
    }

    @Override
    public void deposit(double amount) {
        this.amount += amount;
    }

}
