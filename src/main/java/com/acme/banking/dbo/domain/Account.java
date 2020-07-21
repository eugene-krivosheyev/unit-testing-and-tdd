package com.acme.banking.dbo.domain;


public interface Account {
    Long getId();
    Long getClientId(); //TODO reference integrity

    void withdraw(Double amount);

    void charge(Double amount);

    Double getBalance();
}
