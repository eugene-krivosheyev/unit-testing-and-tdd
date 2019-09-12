package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.error.EmptyBranchException;
import com.acme.banking.dbo.error.EmptyClientException;

import java.util.ArrayList;
import java.util.List;

public class ReportingServiceImpl implements ReportingService {

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    @Override
    public String getReport(Branch branch) throws EmptyBranchException, EmptyClientException {
        if (branch == null) {
            throw new EmptyBranchException("Branch is null");
        }

        return getFormattedReport(branch, 1);
    }

    private String getFormattedReport(Branch branch, int level) throws EmptyClientException {
        StringBuilder levelString = new StringBuilder();
        StringBuilder levelSpace = new StringBuilder();

        for (int i = 1; i <= level; i++) {
            levelString.append("#");
            levelSpace.append(" ");
        }

        StringBuilder result = new StringBuilder();
        result.append(String.format("%s %s: %s %s", levelString.toString(), branch.getId(), branch.getName(), branch.getAccounts().size()));
        result.append(System.lineSeparator());

        List<Account> accounts = new ArrayList<>(branch.getAccounts());
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (i > 0) {
                result.append(System.lineSeparator());
            }

            if (account.getClient() == null) {
                throw new EmptyClientException("Client is null");
            }

            result.append(levelSpace);
            result.append(String.format("- %s, Client: %s", account.getAmount(), account.getClient().getName()));
        }

        List<Branch> branches = new ArrayList<>(branch.getChildren());
        for (int i = 0; i < branches.size(); i++) {
            if (i > 0) {
                result.append(System.lineSeparator());
            }
            result.append(levelSpace);
            result.append(getFormattedReport(branches.get(i), level + 1));
        }

        return result.toString().trim();
    }
}
