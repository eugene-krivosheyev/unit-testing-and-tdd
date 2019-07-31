package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

public class Reporting {
    private Integer state = 1;

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Client rootBranch) {
        return null;
    }
}
