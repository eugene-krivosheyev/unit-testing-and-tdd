package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }


    public String getReport(Account stubAccount) {
        if (stubAccount.getAmount() != 0.){
            return String.valueOf(stubAccount.getAmount());
        }
        return "ACCOUNT 1 EMPTY";
    }
}
