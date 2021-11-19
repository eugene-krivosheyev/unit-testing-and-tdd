package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) throws IllegalArgumentException{
        if (Integer.signum(id) == -1) throw new IllegalArgumentException("id is negative");
        if (client == null) throw new IllegalArgumentException("client is null");
        if (amount < 0) throw new IllegalArgumentException("negative amount");

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
