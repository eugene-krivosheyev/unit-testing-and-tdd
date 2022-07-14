package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity
    void debit(double amount);
    void issue(double amount);
}
