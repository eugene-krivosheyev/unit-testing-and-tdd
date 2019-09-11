package com.acme.banking.dbo.dal;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AccountRepo {
    Collection<Account> findAccountsByUUID(UUID clientId);

    SavingAccount findAccountById(UUID fromAccountId);

    void saveAccounts(List<SavingAccount> accounts);

    UUID createClient(String name);
}
