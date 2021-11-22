package com.acme.banking.dbo.repositories;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public interface ClientRepository {

    Collection<Account> getAccountsByClientId(int clientId);

    Client saveClient(Client client);
}
