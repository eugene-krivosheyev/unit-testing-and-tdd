package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.UUID;

public interface ClientRepository {
    UUID add(Client client);

    Client findById(UUID clientId) throws ClientNotFoundException;
}
