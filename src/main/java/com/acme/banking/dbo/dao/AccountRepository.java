package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Account;

import java.util.UUID;

public interface AccountRepository {
    Account findById(UUID id) throws AccountNotFoundException;

    void update(Account fromAccount);
}
