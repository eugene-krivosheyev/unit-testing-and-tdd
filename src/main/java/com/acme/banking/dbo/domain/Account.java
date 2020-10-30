package com.acme.banking.dbo.domain;

import java.util.UUID;

public interface Account {
    UUID getId();
    double getAmount();
    void setAmount(double amount);
    Client getClient(); //TODO reference integrity
}
