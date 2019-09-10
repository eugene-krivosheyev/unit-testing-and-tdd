package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.errors.UniqueConstraintException;

import java.util.UUID;

public interface Account {
    UUID getId();
    UUID getClientId();
    void removeClient();
    void linkTo(Client client) throws UniqueConstraintException;
}
