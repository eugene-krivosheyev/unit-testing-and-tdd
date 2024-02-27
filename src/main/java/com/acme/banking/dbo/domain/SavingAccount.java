package com.acme.banking.dbo.domain;

import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NEGATIVE_AMOUNT_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NEGATIVE_ID_MESSAGE;
import static com.acme.banking.dbo.domain.Errors.ACCOUNT_NULL_CLIENT_MESSAGE;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) {
            throw new IllegalArgumentException(ACCOUNT_NEGATIVE_ID_MESSAGE);
        }
        if (client == null) {
            throw new IllegalArgumentException(ACCOUNT_NULL_CLIENT_MESSAGE);
        }
        if (amount < 0) {
            throw new IllegalArgumentException(ACCOUNT_NEGATIVE_AMOUNT_MESSAGE);
        }
        this.id = id;
        this.client = client;
        this.amount = amount;
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
