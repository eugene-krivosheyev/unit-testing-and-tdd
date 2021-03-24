package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if(id == 0) throw new IllegalArgumentException("id can't be zero");
        if(client == null) throw new IllegalArgumentException("CLIENT SHUNT BE NULL");
        if(amount <= 0) throw new IllegalArgumentException("Amount shouldn't be less zero");

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

    @Override
    public Account withdraw(double amount) {
        return null;
    }

    @Override
    public Account deposit(double amount) {
        return null;
    }

    @Override
    public void setAmount(double v) {
        this.amount = v;
    }
}
