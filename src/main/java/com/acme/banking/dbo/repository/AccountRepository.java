package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {

    public Account findAccountById(int accountId);

    public void saveAccount(Account account);
}
