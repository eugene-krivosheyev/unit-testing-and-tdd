package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        StringBuilder result = new StringBuilder("# Branch #" + rootBranch.getName());
        for (Account account : rootBranch.getAccounts()) {
            result.append("\n## Account #").append(account.getId())
                    .append(" (").append(account.getAmount()).append(")");
        }
        for (Branch child : rootBranch.getChildren()) {
            result.append("\n## Branch #").append(child.getName());
            for (Account account : child.getAccounts()) {
                result.append("\n### Account #").append(account.getId())
                        .append(" (").append(account.getAmount()).append(")");
            }
        }
        return result.toString();
    }
}
