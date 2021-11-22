package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;


public interface AccountRepository {
    Collection<Account> getAccountsByClientId(int clientId);
    Account getAccountById(int accountId);
    void save(Account account);
}
