package com.acme.banking.dbo.domain;


import jdk.nashorn.internal.ir.annotations.Ignore;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {

        if(id == 0) throw new IllegalArgumentException("id = 0");
        if(client == null) throw new IllegalArgumentException("client = null");
        if (amount >= Double.MAX_VALUE) throw new IllegalArgumentException("amount > Double.MAX_VALUE");
        if (amount <= -Double.MAX_VALUE) throw new IllegalArgumentException("amount < Double.MIN_VALUE");

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
    @Ignore
    public void withdraw(double amount) {

    }

    @Override
    @Ignore
    public void deposit(double amount) {

    }
}
