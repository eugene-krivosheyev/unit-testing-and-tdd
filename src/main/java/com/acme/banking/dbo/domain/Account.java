package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    int getId();

    void withdraw(double amount);
    void credit(double amount);

    double getAmount();
}
