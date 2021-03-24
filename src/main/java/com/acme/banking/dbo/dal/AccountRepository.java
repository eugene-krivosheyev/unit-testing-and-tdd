package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {
    Account findById(int accountId);
    void save(Account account);
}
