package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

import java.util.stream.Collectors;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        if (rootBranch == null) {
            return "Empty brunch";
        } else if (rootBranch.getAccounts().isEmpty()) {
            return rootBranch.getName();
        } else {
            StringBuilder result = new StringBuilder();
            result.append(rootBranch.getName());
            String accountNames = rootBranch.getAccounts().stream().map(account -> account.getId().toString()).collect(Collectors.joining(","));
            return result.append(accountNames).toString();
        }
    }
}
