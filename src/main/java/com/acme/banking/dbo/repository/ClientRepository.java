package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;

public interface ClientRepository {
    Client save(Client client);
    Account findById(int fromAccountId);
    void update(Account updatedAccount);
}