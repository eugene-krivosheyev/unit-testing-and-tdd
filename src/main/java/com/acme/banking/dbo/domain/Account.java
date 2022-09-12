package com.acme.banking.dbo.domain;

import java.util.Collection;

public interface Account {
    int getId();
    double getAmount();
    void setAmount(double amount);
    Client getClient(); //TODO reference integrity
    void save(Account account);
}
