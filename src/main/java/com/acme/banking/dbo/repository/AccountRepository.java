package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<Account> findAllByClientId(int clientId);

    Optional<Account> findByAccountId(int accountId);

    Account save(Account from);

}
