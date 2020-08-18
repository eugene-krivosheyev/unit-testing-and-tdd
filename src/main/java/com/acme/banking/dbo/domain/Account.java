package com.acme.banking.dbo.domain;


public interface Account {
    Integer getId();
    Integer getClientId(); //TODO reference integrity
    double getAmount();

    void withdraw(double amount);
    void deposit(double amount);
    void getAccountReport();
}
