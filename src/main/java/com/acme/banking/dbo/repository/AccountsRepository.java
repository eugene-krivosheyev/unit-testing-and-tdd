package com.acme.banking.dbo.repository;

import com.acme.banking.dbo.domain.Account;

import java.util.List;
import java.util.UUID;

public interface AccountsRepository {
    Account findById(UUID id);
    List<Account> findAccountsByClientId(UUID clientId);
    UUID transfer(double amount, Account fromAccount, Account toAccount);
}
