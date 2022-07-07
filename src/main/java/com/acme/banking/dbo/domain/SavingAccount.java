package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final int id;
    private final Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id <= 0) {
            throw new IllegalArgumentException("id is not positive");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("amount is negative");
        }
        if (client == null) {
            throw new IllegalArgumentException("client is null");
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

    public void addAccountToClient() {
        client.getAccounts().add(this);
    }
}


