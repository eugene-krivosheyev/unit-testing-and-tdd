package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private Client client;
    private double amount;

    public SavingAccount(Client client, double amount) {
        this.client = client;
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getClientId() {
        return client.getId();
    }

    @Override
    public void withdraw(double amount) {
        this.amount = this.amount - amount;
    }

    @Override
    public void deposit(double amount) {
        this.amount = this.amount + amount;
    }


}
