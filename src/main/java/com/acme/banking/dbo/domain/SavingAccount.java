package com.acme.banking.dbo.domain;

public class SavingAccount implements Account {
    private final static String ARG_EXCEPTION_MESSAGE_ID_NEGATIVE = "ID is expected to be positive int";
    private final static String ARG_EXCEPTION_MESSAGE_AMOUNT_NEGATIVE = "Amount is expected to be positive double";
    private final static String ARG_EXCEPTION_MESSAGE_CLIENT_NULL = "Client is NULL";

    final private int id;
    final private Client client;
    final private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_ID_NEGATIVE);
        if (amount < 0) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_AMOUNT_NEGATIVE);
        if (client == null) throw new IllegalArgumentException(ARG_EXCEPTION_MESSAGE_CLIENT_NULL);

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
