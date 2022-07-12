package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;

public interface AccountRepository {
    public Collection<Account> findClientById(int clientId);
}
