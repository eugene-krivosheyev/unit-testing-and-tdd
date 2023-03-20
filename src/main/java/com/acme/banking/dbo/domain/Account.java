package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity

    void minusCash(double amount);

    void plusCash(double amount);
}
