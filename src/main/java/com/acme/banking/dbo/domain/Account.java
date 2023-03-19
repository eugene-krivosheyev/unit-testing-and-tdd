package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient();
    Account withdraw(double amount);
    Account deposit(double amount);
}
