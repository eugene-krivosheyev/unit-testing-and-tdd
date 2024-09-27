package com.acme.banking.dbo.repository;

import java.util.Optional;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {

    Account registerAccount(Account account);
    Optional<Account> getAccount(int accountId);
}