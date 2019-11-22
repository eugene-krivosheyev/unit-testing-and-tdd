package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        String report = addBranchNameToReport(rootBranch.getName());

        if (!rootBranch.getAccounts().isEmpty()) {
            report += report + "## clientName";
        }
        return report;
    }

    private String addBranchNameToReport(String branchName) {
        if (branchName == null) return "# ";
        return "# " + branchName;
    }
}
