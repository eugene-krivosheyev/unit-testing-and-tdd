package com.acme.banking.dbo.domain;


public interface Account {
    Integer getId();
    Integer getClientId(); //TODO reference integrity
}
