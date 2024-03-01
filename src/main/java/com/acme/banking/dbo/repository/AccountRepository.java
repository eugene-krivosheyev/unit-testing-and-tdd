package com.acme.banking.dbo.repository;

import java.util.Collection;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {
    Collection<Account> getAccountsArray(int clientId);
}