package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepository {

    Account findById(int id);
}
