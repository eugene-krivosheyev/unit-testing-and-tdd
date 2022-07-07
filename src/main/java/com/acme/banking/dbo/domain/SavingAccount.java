package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private final double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id <= 0) throw new IllegalArgumentException("Id shouldn't be 0");
        if (client == null) throw new IllegalArgumentException("Client shouldn't be null");
        if (amount <= 0) throw new IllegalArgumentException("Amount shouldn't negative");

        this.id = id;
        this.client = client;
        this.amount = amount;
        client.getAccounts().add(this);
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
