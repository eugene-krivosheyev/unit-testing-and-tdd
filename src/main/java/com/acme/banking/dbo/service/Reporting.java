package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.Collection;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        StringBuilder report = new StringBuilder(addBranchNameToReport(rootBranch.getName()));
        final Collection<Account> accounts = rootBranch.getAccounts();
        if (accounts.isEmpty()) return report.toString();
        for (Account eachAccount : accounts) {
            report.append("## ").append(eachAccount.getId());

            if (eachAccount.getClient()==null || eachAccount.getClient().getAccounts()==null) continue;
            for (SavingAccount eachSavingAccount : eachAccount.getClient().getAccounts()) {
                report.append("### savingAccountId");
            }
        }
        return report.toString();
    }

    private String addBranchNameToReport(String branchName) {
        if (branchName == null) return "# \n";
        return "# " + branchName + "\n";
    }
}
