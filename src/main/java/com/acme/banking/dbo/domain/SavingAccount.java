package com.acme.banking.dbo.domain;

import static java.util.Objects.requireNonNull;

public final class SavingAccount implements Account {

    private final Client client;
    private final double amount;
    private final int id;

    public SavingAccount(int id, Client client, double amount) {
        if (amount < 0) throw new IllegalArgumentException("Parameter 'amount' is negative.");
        this.client = requireNonNull(client, "Parameter 'client' must not be null.");
        this.amount = amount;
        this.id = id;
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
}