package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getAmount();
    Client getClient();
    void setClient(Client newClient);

    void changeBalance(double amount);
}
