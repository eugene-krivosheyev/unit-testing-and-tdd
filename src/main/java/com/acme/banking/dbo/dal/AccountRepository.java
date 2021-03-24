package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Account;

import java.util.UUID;

public interface AccountRepository {
    Account findById(int id);

    void save(Account account);
}
