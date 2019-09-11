package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    private AccountRepository accounts = new JdbcAccountRepo(); //Creator [GRASP]

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }

    public String getReport(Account account) throws AccountNotFoundException {
        final Account foundAccount = accounts.findById(account.getId());
        return "## " + foundAccount.getId() + " " + foundAccount.getClientId();
    }
}
