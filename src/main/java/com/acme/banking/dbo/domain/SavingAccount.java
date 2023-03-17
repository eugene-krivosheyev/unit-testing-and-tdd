package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if ( id < 0 ) {
            throw new IllegalArgumentException("id should not be less than 0");
        }
        if ( client == null ) {
            throw new IllegalArgumentException("client should not be null");
        }
        if ( amount < 0 ) {
            throw new IllegalArgumentException("amount should not be less than 0");
        }
        this.id = id;
        this.client = client;
        this.amount = amount;
        client.saveAccountForClient(this);
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

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
