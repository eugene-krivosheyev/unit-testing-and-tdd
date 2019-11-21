package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dal.ClientRepository;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

public class Reporting {
    private ClientRepository clients;

    public Reporting(ClientRepository clients) {
        this.clients = clients;
    }

    /**
     * @return Markdown report for all branches, clients, accounts
     */
    public String getReport(Branch rootBranch) {
        return null;
    }

    public String getReport(UUID id) {
        final Client foundClient = clients.findById(id);
        return
                "## " + foundClient.getId() + " " + foundClient.getName() +
                "### 1 100.";
    }
}
