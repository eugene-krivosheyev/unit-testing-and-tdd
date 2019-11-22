package com.acme.banking.dbo.domain;

import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public class Branch {
    private String name;
    private Collection<Branch> children;

    public Branch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private Collection<Account> accounts; //TODO impl

    public Branch(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Account> getAccounts() {
        return unmodifiableCollection(accounts);
    }

    public Collection<Branch> getChildren() {
        return unmodifiableCollection(children);
    }
}
