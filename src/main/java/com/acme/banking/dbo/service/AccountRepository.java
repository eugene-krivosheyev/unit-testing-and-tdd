package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;

import java.util.UUID;

public interface AccountRepository {
    Account findById(UUID id);
    void save(Account account);
}
