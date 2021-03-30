package com.acme.banking.dbo.domain;

import java.util.Collection;
import java.util.UUID;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity

    void withdraw(double amount);

    void deposit(double amount);

    String getName();
}
