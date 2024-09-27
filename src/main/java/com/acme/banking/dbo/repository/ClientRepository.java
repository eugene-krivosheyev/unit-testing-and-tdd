package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client save(Client client);

    Client getById(int id);

    int nextId();
}
