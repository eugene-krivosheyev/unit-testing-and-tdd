package com.acme.banking.dbo.domain;

import java.util.Objects;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (client == null) {
            throw new IllegalArgumentException("client can not be null");
        }
        if (id < 0){
            throw new IllegalArgumentException("Id can not less then 0");
        }
        if (amount < 0){
            throw new IllegalArgumentException("Amount can not less then 0");
        }
        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public double withDraw(double transferAmount){
        if (amount - transferAmount < 0) {
            throw new IllegalArgumentException("you do not have money");
        }
        return this.amount = amount - transferAmount;
    }

    public double deposit(double transferAmount){
        return this.amount = amount + transferAmount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavingAccount that = (SavingAccount) o;
        return id == that.id && Double.compare(amount, that.amount) == 0 && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, amount);
    }

    @Override
    public Client getClient() {
        return client;
    }
}
