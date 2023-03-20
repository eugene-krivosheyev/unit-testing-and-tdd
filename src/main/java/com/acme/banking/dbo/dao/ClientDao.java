package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Client;

public interface ClientDao {
    Client saveClient(String clientName);

    Client selectClientById(int clientId);
}
