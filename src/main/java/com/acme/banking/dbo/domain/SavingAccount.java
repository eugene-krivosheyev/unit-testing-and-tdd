package com.acme.banking.dbo.domain;

import static java.util.Objects.isNull;

public class SavingAccount implements Account {
    private long id;
    private Client client;
    private double amount;

    public SavingAccount(Long id, Client client, Double amount) {
        if (isNull(id) || id < 0) throw new IllegalArgumentException();
        if (isNull(amount) || amount < 0) throw new IllegalArgumentException();
        if (isNull(client)) throw new IllegalArgumentException();

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
    public long getId() {
        return id;
    }

    @Override
    public long getClientId() {
        return client.getId();
    }
}
