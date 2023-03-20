package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Account;

public interface AccountDao {
    Account selectById(int fromAccountId);

    void updateAmount(Account accountTransfer);
}
