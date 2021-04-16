package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return "# Branch #1";
    }

//    /**
//     * @return Markdown report for all branches, clients, accounts
//     */
//    public String getReport(Branch rootBranch) {
//        StringBuilder result = new StringBuilder("# " + rootBranch.getName());
//        for (Account account : rootBranch.getAccounts()) {
//            result.append("## Account #").append(account.getId())
//                    .append(" (").append(account.getAmount()).append(")");
//        }
//        return result.toString();
//    }
}
