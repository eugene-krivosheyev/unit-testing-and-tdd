package com.acme.banking.dbo.domain;


public interface Account {
    long getId();

    long getClientId(); //TODO reference integrity

    void withdraw(double amount);
    void deposit(double amount);
    double getAmount();
}
