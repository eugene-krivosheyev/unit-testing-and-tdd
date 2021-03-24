package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private Collection<Account> accounts; //TODO impl
    private String name;

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return null; //TODO
    }

    public String getName() {
        return this.name;
    }
}
