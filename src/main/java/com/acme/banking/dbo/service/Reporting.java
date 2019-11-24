package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import java.util.Collection;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        StringBuilder report = new StringBuilder(addBranchNameToReport(rootBranch.getName()));
        final Collection<Client> clients = rootBranch.getClients();
        if (clients.isEmpty()) return report.toString();
        for (Client eachClient : clients) {
            report.append("## ").append(eachClient.getId());

            if (eachClient.getAccounts()==null) continue;
            for (SavingAccount eachSavingAccount : eachClient.getAccounts()) {
                report.append("### savingAccountId");
            }
        }
        return report.toString();
    }

    private String addBranchNameToReport(String branchName) {
        if (branchName == null) return "# \n";
        return "# " + branchName + "\n";
    }
}
