package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class Branch {

    private final Collection<Account> accounts;
    private final String name;

    public Branch(Collection<Account> accounts, String name) {
        this.accounts = accounts;
        this.name = name;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return null; //TODO
    }

    public Collection<Client> getBranchClients() {
        return accounts.stream()
                .map(Account::getClient)
                .collect(toUnmodifiableSet());
    }

    public String getName() {
        return name;
    }
}