package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {

        String reportName = rootBranch.getName();
        if (reportName == null) reportName = "";
        String report = "# " + reportName;

        if (!rootBranch.getAccounts().isEmpty()) {
            report += report + "## clientName";
        }
        return report;
    }
}
