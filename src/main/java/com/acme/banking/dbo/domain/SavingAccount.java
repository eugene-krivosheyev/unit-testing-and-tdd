package com.acme.banking.dbo.domain;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        if(client == null) {
            throw new IllegalArgumentException("Client should be exist");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount is not negative meaning");
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
    public void setAmount(double amount) {
        this.amount = amount;
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
