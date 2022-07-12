package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.domain.Account;

public interface AccountRepository {

    Account getAccountById(int accountId);

}
