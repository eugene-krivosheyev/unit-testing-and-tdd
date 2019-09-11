package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.AccountNotFoundException;
import com.acme.banking.dbo.dal.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    //Field DI
    private AccountRepository accounts;
    private AuditService auditService;

    //Constructor DI
    public Reporting(AccountRepository accounts, AuditService auditService) {
        this.accounts = accounts;
        this.auditService = auditService;
    }

    //Setter DI
    public void setAccounts(AccountRepository accounts) {
        this.accounts = accounts;
    }


    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }

    public String getReport(Account account) throws AccountNotFoundException {
        final Account foundAccount = accounts.findById(account.getId());
        auditService.log("report for accountid " + foundAccount.getId());
        return "## " + foundAccount.getId() + " " + foundAccount.getClientId();
    }
}
