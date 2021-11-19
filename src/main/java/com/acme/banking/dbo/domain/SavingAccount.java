package com.acme.banking.dbo.domain;

import static java.util.Objects.requireNonNull;

public final class SavingAccount implements Account {

    private final double amount;
    private Client client;
    private final int id;

    public SavingAccount(int id, Client client, double amount) {
        if (amount < 0) throw new IllegalArgumentException("Parameter 'amount' is negative.");
        this.amount = amount;
        this.id = id;

        // Client class set itself like owner for account manually
        requireNonNull(client, "Parameter 'client' must not be null")
                .addAccount(this);
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
    public void setClient(Client client) {
        this.client = requireNonNull(client, "Parameter 'client' must not be null.");
    }

    @Override
    public void removeClient() {
        this.client = null;
    }
}