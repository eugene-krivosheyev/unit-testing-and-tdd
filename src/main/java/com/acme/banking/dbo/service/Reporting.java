package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }

    public String getReport(Client client) {return null;}
}
