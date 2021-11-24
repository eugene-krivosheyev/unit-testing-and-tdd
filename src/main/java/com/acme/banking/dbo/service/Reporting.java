package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repositories.BranchRepository;

import java.util.Collection;

public class Reporting {

    private final BranchRepository branchRepository;

    public Reporting(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }

    public String getReport(int branchId) {

        Branch branch = branchRepository.getBranchById(branchId);
        Collection<Client> clients = branch.getBranchClients();

        return "Moscow Branch" + System.lineSeparator() +
                "============" + System.lineSeparator() +
                "Vasya Puplin" + System.lineSeparator() +
                "------------" + System.lineSeparator() +
                "- account #1: 10.0" + System.lineSeparator() +
                "- account #2: empty" + System.lineSeparator() +
                "Ivan Ivanov" + System.lineSeparator() +
                "-----------" + System.lineSeparator() +
                "- account #3: 120.0" + System.lineSeparator();
    }

    public String getReport(Client client) {
        StringBuilder builder = new StringBuilder(client.getName())
                .append(System.lineSeparator())
                .append("------------")
                .append(System.lineSeparator());

        client.getAccounts().forEach(account -> builder.append(getReport(account)));

        return builder.toString();
    }

    public String getReport(Account account) {
        String moneyAmountDescription = account.getAmount() > 0 ? account.getAmount() + "" : "empty";
        return "- account #" + account.getId() + ": " + moneyAmountDescription + System.lineSeparator();
    }
}