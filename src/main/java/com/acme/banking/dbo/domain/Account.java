package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity

    Account withdraw(double amount);

    Account deposit(double amount);

    void setAmount(double v);
}
