package com.acme.banking.dbo.domain.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient(); //TODO reference integrity
}
