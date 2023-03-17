package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client create(Client client);

    Client findById(int id);
}