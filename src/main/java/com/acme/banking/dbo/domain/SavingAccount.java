package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.utils.ObjectUtils;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;
    private ObjectUtils utils = new ObjectUtils();

    public SavingAccount(UUID id, double amount)
            throws NullPointerException, IllegalArgumentException {

        this.id = utils.requireNonNull(id, "id must not be null or empty");
        this.amount = amount;
    }

    @Override
    public void setClient(Client client) {
        this.client = utils.requireNonNull(client, "client must not be null or empty");
    }

    @Override
    public void removeClient() {
        client = null;
    }

    public Client getClient() {
        return client;
    }

    @Override
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
