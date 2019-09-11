package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

public interface ClientRepository {
    Client findByClientId(UUID id) throws ClientNotFoundException;
    Client saveClient(UUID id, String name);
}
