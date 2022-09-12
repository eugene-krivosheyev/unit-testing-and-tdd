package com.acme.banking.dbo.domain;

import java.util.Collection;

public interface ClientRepository {

    Collection<Account> getAccounts(int clientId);

    Account getAccountByClientId(int clientId);

    int generateNextId();


}
