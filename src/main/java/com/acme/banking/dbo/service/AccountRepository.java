package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import java.util.*;

public interface AccountRepository {

    Collection<Account> findAccountsByClient(int clientId);

    Account updateClientAccount(Account update);

    Account findAccountById(int accountId);
}
