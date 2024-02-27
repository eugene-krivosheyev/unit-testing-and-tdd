package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (client == null) {
            throw new IllegalArgumentException("Client can not be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("Id can not be less than 0");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount can not be less than 0");
        }

        this.id = id;
        this.client = client;
        this.amount = amount;
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
