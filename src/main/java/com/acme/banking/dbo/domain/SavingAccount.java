package com.acme.banking.dbo.domain;


public class SavingAccount implements Account {
    public static final String SAVING_ACCOUNT_NEGATIVE_AMOUNT_ERROR = "Amount should not be negative";

    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {

        // Guard clause
        if (client == null) throw new IllegalArgumentException("Client should not be Null");
        if (id < 0 ) throw new IllegalArgumentException("Client id should not be negative, id:" + id);
        if (amount < 0 ) throw new IllegalArgumentException(SAVING_ACCOUNT_NEGATIVE_AMOUNT_ERROR + ", amount:" + id);

        /* This is not thread-save!
          You may have concurrency problem!
        */
        client.addAccount(this);

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

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
