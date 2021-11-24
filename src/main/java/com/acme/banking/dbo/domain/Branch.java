package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;
import static java.util.stream.Collectors.toUnmodifiableSet;

public class Branch {
    private Collection<Account> accounts; //TODO

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
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
}