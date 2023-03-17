package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {

    public int getLastClientId();

    public void saveClient(Client client);

    public Client getClientById(int id);
}
