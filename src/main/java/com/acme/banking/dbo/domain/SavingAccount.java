package com.acme.banking.dbo.domain;


import static java.util.Objects.isNull;

public class SavingAccount implements Account {
    private long id;
    private Client client;
    private double amount;

    public SavingAccount(Long id, Client client, Double amount) {
        if (isNull(id) || id < 0) throw new IllegalArgumentException("Некорректный или пустой id");
        if (isNull(amount) || amount < 0) throw new IllegalArgumentException("Некорректный или пустая сумма");
        if (isNull(client)) throw new IllegalArgumentException("Пустой клиент");

        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getClientId() {
        return client.getId();
    }

    @Override
    public void sendMoney(double amount) {
        this.amount = this.amount - amount;
    }

    @Override
    public void getMoney(double amount) {
        this.amount = this.amount + amount;
    }
}
