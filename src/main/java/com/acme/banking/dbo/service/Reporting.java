package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.AccountRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.Optional;

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

    public String getClientReport(Client client) {
        String result;

        //String clientName = client.getName();
        Collection<Account> clientAccounts = client.getAccounts();

        Account account = clientAccounts.iterator().next();
        double amount = account.getAmount();

//        for (Account current:clientAccounts) {
//
//        }

        String amountstr = amount == 0 ? String.valueOf(amount) : "empty";

        result = "Ivan Ivanov"+ System.lineSeparator() +
                "-----------" + System.lineSeparator() +
                "- account #3: " + amountstr + System.lineSeparator();
//        Ivan Ivanov
//        -----------
//        - account #3: empty

//         "Ivan Ivanov"+ System.lineSeparator() +
//                "-----------" + System.lineSeparator() +
//                "- account #3: 120.0" + System.lineSeparator();

        return result;
    }

    public String getAccountReport(Account account) {
        int accountId = account.getId();
        double amount = account.getAmount();

        String amountstr = amount != 0 ? String.valueOf(amount) : "empty";

        return "- account #" + accountId + ": " + amountstr + System.lineSeparator();

    }
}
