package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    double getAmount();
    Client getClient(); //TODO reference integrity

    Account withdraw(double amount);

    Account deposit(double amount);
}
