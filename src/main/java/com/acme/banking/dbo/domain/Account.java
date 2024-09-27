package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity
    void deposit(double amount);
    void withdraw(double amount);
}
