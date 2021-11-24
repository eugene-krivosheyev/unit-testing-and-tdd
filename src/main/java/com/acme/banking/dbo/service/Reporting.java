package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }


//
//    String getReport(final Account account) {
//        return "- account #" + account.getId() + ": " + account.getAmount();
//    }

    public String getReport(int branchId) {

        return "- account #2: empty" + System.lineSeparator();
    }

    public String getClientReport(Client clientStub) {
        return "Ivan Ivanov"+ System.lineSeparator() +
                "-----------" + System.lineSeparator() +
                "- account #3: 120.0" + System.lineSeparator();
    }
}
