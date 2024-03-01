package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {

    public int generateId();

    public Client save(Client client);

    public Client getClientById(int id);

}
