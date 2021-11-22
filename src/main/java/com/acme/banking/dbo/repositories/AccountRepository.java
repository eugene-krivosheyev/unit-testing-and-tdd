package com.acme.banking.dbo.repositories;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {

    Account getAccountById(int accountId);

    Account save(Account account);
}
