package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

public interface ClientRepository {
    Client save(Client toSave);
    Account findById(int accountId);
    void update(Account account);
}
