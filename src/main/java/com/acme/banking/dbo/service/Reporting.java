package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;


public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */

    public String getReport(Branch rootBranch) {
        StringBuilder builder = new StringBuilder();
        for (Branch branches :rootBranch.getChildren()) {
            builder.append(branches.getName()).append("\r\n");
            for (Account account: branches.getAccounts()) {
                builder
                        .append(account.getName())
                        .append(" (")
                        .append(account.getAmount())
                        .append(")").append("\r\n")
                        .append(account.getClient().getName()).append("\r\n");

            }
        }
        return builder.toString();
    }

//    public String getReport(Branch rootBranch) {
//
//        StringBuilder builder = new StringBuilder();
//
//        for (Branch branch:rootBranch.getChildren()) {
//            builder.append(" ").append(branch.getName());
//            for (Collection<Account> accounts :branch.getAccounts()) {
//                builder.append("## ").append(accounts.)
//            }
//        }
//        return "# Branch #1" +
//                "## Account #1 (100.)" +
//                "### Client #1";
//    }
}
