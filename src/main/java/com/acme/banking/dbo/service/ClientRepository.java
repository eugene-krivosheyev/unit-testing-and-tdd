package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public interface ClientRepository {
    void save(Client clientStub);

    Client findClientById(int clientId);
}
