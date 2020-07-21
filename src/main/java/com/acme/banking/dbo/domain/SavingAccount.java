package com.acme.banking.dbo.domain;


import static java.util.Objects.isNull;

public class SavingAccount implements Account {
    private Long id;
    private Client client;
    private double amount;

    public SavingAccount(Long id, Client client, double amount) {
        if(id == null || id < 0) throw new IllegalArgumentException("Wrong id");
        if(client == null || client.getClass() != Client.class) throw new IllegalArgumentException();
        if(amount < 0) throw new IllegalArgumentException();
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
    public Long getId() {
        return id;
    }

    @Override
    public Long getClientId() {
        return client.getId();
    }

    @Override
    public void withdraw(Double amount) {

    }

    @Override
    public void charge(Double amount) {

    }

    @Override
    public Double getBalance() {
        return null;
    }

}
