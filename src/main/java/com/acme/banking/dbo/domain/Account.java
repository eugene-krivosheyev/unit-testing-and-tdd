package com.acme.banking.dbo.domain;

public interface Account {
    int getId();

    Integer getClientId(); //TODO reference integrity

    void withdraw(double amount);

    void deposit(double amount);
}
