
package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;

public interface ClientRepository {
    int initClientId();
    Client getClientById(int accountId);
    void save(Client client);
    Collection<Account> getAccountsByClientId(int clientId);
    void save();
}