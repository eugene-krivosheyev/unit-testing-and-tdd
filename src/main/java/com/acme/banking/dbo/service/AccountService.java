package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.SavingAccount;
import java.util.Collections;
import java.util.List;

public class AccountService {


    public List<Account> getAccountsByClientId(int id) {
        return Collections.emptyList();
    }

    public Account getAccountById(int id) {
        return new SavingAccount();
    }

    public Account updateAccount(Account account) {
        return account;
    }
}
