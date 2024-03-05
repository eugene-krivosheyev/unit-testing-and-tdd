package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        if (rootBranch == null){
            return "EMPTY REPORT";
        }
        if (rootBranch.getAccounts() == null){
            throw new IllegalArgumentException("Branch should have at least one account");
        }
        return "EMPTY REPORT";
    }
}
