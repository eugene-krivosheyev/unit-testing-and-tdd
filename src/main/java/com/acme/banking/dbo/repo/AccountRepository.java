package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Account;

import java.util.Collection;

public interface AccountRepository {

    public Collection<Account> getClientAccounts(int clientId);

}
