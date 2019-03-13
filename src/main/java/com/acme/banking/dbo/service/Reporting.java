package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    private Branch branch;

    public Reporting(Branch branch) {
        this.branch = branch;
    }

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport() {
        return "# Report" + "\n" + "## Branch: " + branch.getName();
    }
}
