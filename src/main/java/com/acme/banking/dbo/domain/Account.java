package com.acme.banking.dbo.domain;

public interface Account {
    int getId();

    double getAmount();

    void setAmount(double amount);

    Client getClient(); //TODO reference integrity

    void setClient(Client client);

    void removeClient();
}
