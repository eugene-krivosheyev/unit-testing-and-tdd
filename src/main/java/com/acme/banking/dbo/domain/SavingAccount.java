package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private Integer id;
    private Client client;
    private double amount;

    public SavingAccount(Integer id, Client client, double amount) {
        if (id == null) throw new IllegalArgumentException("Error: id is null");
        if (client == null) throw new IllegalArgumentException("Error: client is null");
        if (amount < 0) throw new IllegalArgumentException("Error: anount < 0");

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
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getClientId() {
        return client.getId();
    }

    @Override
    public void withdraw(double amount) {

    }
    @Override
    public void deposit(double amount) {

    }


}
