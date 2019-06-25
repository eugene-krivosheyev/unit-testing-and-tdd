package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.repo.AccountRepository;

import java.util.UUID;

public class Reporting {
    //Creational responsibility:
    //Creator [GRASP]
    //Factory Method [GoF] -> Abstract Factory [GoF] -> Registry [PoEAA]
    //Field DI
    private AccountRepository accounts;

    //Constructor DI
    public Reporting(AccountRepository accounts) {
        this.accounts = accounts;
    }

    //Setter DI
    public void setAccounts(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public String getReport(UUID accountId) {
        Account account = accounts.findById(accountId);
        return "# *" + account.getId() + "* " + account.getClientId();
    }
}
