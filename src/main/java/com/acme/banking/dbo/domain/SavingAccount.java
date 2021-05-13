package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id >= 0) {
            if (client != null) {
                if (amount >= 0) {
                    this.id = id;
                    this.client = client;
                    this.amount = amount;
                }
            }
        }
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
