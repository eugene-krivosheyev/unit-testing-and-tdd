package com.acme.banking.dbo.domain;

public interface Account {
    int getId();

    double getAmount();

    Client getClient(); //TODO reference integrity

    public double withDraw(double transferAmount);

    public double deposit(double transferAmount);
}
