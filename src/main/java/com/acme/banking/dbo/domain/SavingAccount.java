package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.exception.saving_account.SavingAccountClientNullException;
import com.acme.banking.dbo.exception.saving_account.SavingAccountNegativeAmountException;
import com.acme.banking.dbo.exception.saving_account.SavingAccountInvalidIdException;

import java.util.Objects;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (id < 0) throw new SavingAccountInvalidIdException("Id can't be negative");
        if (client == null) throw new SavingAccountClientNullException("Client is null");
        if (amount < 0) throw new SavingAccountNegativeAmountException("Amount can't be negative");

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
}
