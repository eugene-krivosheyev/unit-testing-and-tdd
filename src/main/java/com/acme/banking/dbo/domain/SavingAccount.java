package com.acme.banking.dbo.domain;

//import java.util.Integer;

public class SavingAccount implements Account {
    private Integer id;
    private Client client;
    private double amount;

    public SavingAccount(Integer id, Client client, double amount) {
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
}
