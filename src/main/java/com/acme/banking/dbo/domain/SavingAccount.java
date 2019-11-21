package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private UUID id;
    private double amount;

    public SavingAccount(UUID id, double amount) {
        if (id == null) throw new IllegalArgumentException("id must not null");
        if (amount < 0) throw new IllegalArgumentException("amount must positive value"); ;

        this.id = id;
        this.amount = amount;
    }


    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

}
