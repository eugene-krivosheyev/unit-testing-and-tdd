package com.acme.banking.dbo.domain;


public interface Account {
    long getId();

    long getClientId(); //TODO reference integrity
}
