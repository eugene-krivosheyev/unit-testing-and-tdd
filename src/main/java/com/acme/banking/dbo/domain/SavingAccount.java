package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public static class Err {
        public static final String BAD_ID = "Bad id";
        public static final String BAD_CLIENT = "Bad client";
        public static final String BAD_AMOUNT = "Bad amount";
    }


    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) throw new IllegalArgumentException(Err.BAD_ID);
        if (client == null) throw new IllegalArgumentException(Err.BAD_CLIENT);
        if (amount < 0) throw new IllegalArgumentException(Err.BAD_AMOUNT);

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
