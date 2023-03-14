package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        checkArguments(id, client, amount);
        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    private void checkArguments(int id, Client client, double amount) {
        if (id < 0 || amount < 0 || client == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
