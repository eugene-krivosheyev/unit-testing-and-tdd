package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private Long id;
    private Client client;
    private double amount;

    public SavingAccount(Long id, Client client, double amount) {

        if ( id == null || id < 0 ) throw new IllegalArgumentException();
        if ( client == null ) throw new IllegalArgumentException();
        if ( amount < 0 ) throw new IllegalArgumentException();

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
}
