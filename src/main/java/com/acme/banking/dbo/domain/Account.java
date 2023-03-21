package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient();
    void minusAmount(double amount);
    void plusAmount(double amount);
}
