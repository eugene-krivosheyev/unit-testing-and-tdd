package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.utils.ObjectUtils;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;
    private ObjectUtils utils = new ObjectUtils();

    public SavingAccount(UUID id, Client client, double amount) {
        this.id = utils.requireNonNull(id, "id must not be null or empty");
        this.client = utils.requireNonNull(client, "client must not be null or empty");
        this.amount = amount;
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
