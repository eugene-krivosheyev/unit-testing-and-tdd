package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null) throw new IllegalArgumentException("id is null");
        if (client == null) throw new IllegalArgumentException("client is null");
        if (amount < 0) throw new IllegalArgumentException("amount less than 0");

        this.id = id;
        this.client = client;
        this.amount = amount;
        this.client.addIdToClientAccountIds(this);
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getClientId() {
        return client.getId();
    }

}

