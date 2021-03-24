package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;
import java.util.UUID;

public interface AccountRepository {
    Collection<Account> getAllAccountsByClientId(UUID clientId);
}
