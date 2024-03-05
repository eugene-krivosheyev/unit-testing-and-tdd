package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {
    Account findByAccountId(int accountId);

    void save(Account fromAccount);
}
