package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.errors.UniqueConstraintException;
import com.acme.banking.dbo.utils.ObjectUtils;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private Client client;
    private double amount;
    private ObjectUtils utils = new ObjectUtils();

    public SavingAccount(UUID id, Client client, double amount)
            throws NullPointerException, IllegalArgumentException {

        this.id = utils.requireNonNull(id, "id must not be null or empty");
        this.client = utils.requireNonNull(client, "client must not be null or empty");
        this.amount = amount;
    }

    @Override
    public void linkTo(Client client) throws UniqueConstraintException {
        client.addAccount(this);
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

    @Override
    public void removeClient() {
        client = null;
    }
}
