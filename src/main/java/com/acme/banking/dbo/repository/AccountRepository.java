package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {
    Account save(Account account);

    Account getById(int id);
}
