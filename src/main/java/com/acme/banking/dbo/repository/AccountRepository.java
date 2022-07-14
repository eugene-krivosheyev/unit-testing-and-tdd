package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {
    Account findById(int id);

    Account save(Account account);
}
